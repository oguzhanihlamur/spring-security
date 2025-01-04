package dev.antozy.conventers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashSet;
import java.util.Set;

@Converter
public class JsonConverter implements AttributeConverter<Set<String>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<HashSet<String>>() {});
        } catch (JsonProcessingException e) {
            return new HashSet<>();
        }
    }
}