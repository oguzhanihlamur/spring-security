package dev.antozy.services;

import dev.antozy.repositories.RegisteredClientRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomTokenCustomizerImpl implements CustomTokenCustomizer {

    private final CustomUserDetailsService customUserDetailsService;

    private final RegisteredClientRecordRepository registeredClientRepository;

    @Override
    public void customize(JwtEncodingContext context) {
        if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
            RegisteredClient registeredClient = context.getRegisteredClient();
            Set<String> roles = customUserDetailsService.getUserRoles(registeredClientRepository.findByClientId(registeredClient.getClientId()).get().getUser().getId());

            context.getClaims().claims(claims -> {
                claims.put("roles", roles);
                claims.put("token_type", "Bearer");
                claims.put("timestamp", Instant.now().toString());
            });
        }
    }
}