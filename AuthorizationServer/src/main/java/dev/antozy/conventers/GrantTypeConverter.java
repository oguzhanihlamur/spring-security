package dev.antozy.conventers;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Converter
@Component
public class GrantTypeConverter implements AttributeConverter<Set<AuthorizationGrantType>, Set<String>> {
    @Override
    public Set<String> convertToDatabaseColumn(Set<AuthorizationGrantType> attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.stream()
                .map(AuthorizationGrantType::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AuthorizationGrantType> convertToEntityAttribute(Set<String> dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.stream()
                .map(AuthorizationGrantType::new)
                .collect(Collectors.toSet());
    }
}
