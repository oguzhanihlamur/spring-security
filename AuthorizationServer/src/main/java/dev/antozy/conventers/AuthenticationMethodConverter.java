package dev.antozy.conventers;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Converter
@Component
public class AuthenticationMethodConverter implements AttributeConverter<Set<ClientAuthenticationMethod>, Set<String>> {
    @Override
    public Set<String> convertToDatabaseColumn(Set<ClientAuthenticationMethod> attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.stream()
                .map(ClientAuthenticationMethod::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ClientAuthenticationMethod> convertToEntityAttribute(Set<String> dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.stream()
                .map(ClientAuthenticationMethod::new)
                .collect(Collectors.toSet());
    }
}