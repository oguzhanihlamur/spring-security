package dev.antozy.config;

import dev.antozy.conventers.AuthenticationMethodConverter;
import dev.antozy.conventers.GrantTypeConverter;
import dev.antozy.entities.RegisteredClientRecord;
import dev.antozy.repositories.RegisteredClientRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Configuration
@Component
@RequiredArgsConstructor
public class CustomRegisteredClient {

    private final RegisteredClientRecordRepository registeredClientRecordRepository;

    private final AuthenticationMethodConverter authMethodConverter;

    private final GrantTypeConverter grantTypeConverter;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new RegisteredClientRepository() {
            @Override
            public void save(RegisteredClient registeredClient) {
                RegisteredClientRecord registeredClientRecord = new RegisteredClientRecord(
                        registeredClient.getId(),
                        registeredClient.getClientId(),
                        registeredClient.getClientName(),
                        passwordEncoder.encode(registeredClient.getClientSecret()),
                        registeredClient.getRedirectUris(),
                        registeredClient.getScopes(),
                        authMethodConverter.convertToDatabaseColumn(registeredClient.getClientAuthenticationMethods()),
                        grantTypeConverter.convertToDatabaseColumn(registeredClient.getAuthorizationGrantTypes()),
                        null,
                        null
                );
                registeredClientRecordRepository.save(registeredClientRecord);
            }

            @Override
            public RegisteredClient findById(String id) {
                RegisteredClientRecord registeredClientRecord = registeredClientRecordRepository.findById(id).orElseThrow(() -> new NullPointerException("Verilen ID bilgisine ait Client bulunamadı. ID : " + id));

                return getRegisteredClient(registeredClientRecord);
            }

            @Override
            public RegisteredClient findByClientId(String clientId) {
                RegisteredClientRecord registeredClientRecord = registeredClientRecordRepository.findActiveByClientId(clientId).orElseThrow(() -> new NullPointerException("Verilen CLIENT_ID bilgisine ait Client bulunamadı. CLIENT_ID : " + clientId));

                return getRegisteredClient(registeredClientRecord);
            }
        };
    }

    private RegisteredClient getRegisteredClient(RegisteredClientRecord registeredClientRecord) {
        return RegisteredClient
                .withId(registeredClientRecord.getId())
                .clientId(registeredClientRecord.getClientId())
                .clientName(registeredClientRecord.getClientName())
                .clientSecret(registeredClientRecord.getSecret())
                .clientAuthenticationMethods((set -> set.addAll(authMethodConverter.convertToEntityAttribute(registeredClientRecord.getAuthenticationMethods()))))
                .authorizationGrantTypes((set -> set.addAll(grantTypeConverter.convertToEntityAttribute(registeredClientRecord.getGrantTypes()))))
                .redirectUris((set -> set.addAll(registeredClientRecord.getRedirectUris())))
                .scopes((set -> set.addAll(registeredClientRecord.getScopes())))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                        .accessTokenTimeToLive(Duration.ofHours(5))
                        .refreshTokenTimeToLive(Duration.ofDays(1))
                        .reuseRefreshTokens(false)
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();
    }

}
