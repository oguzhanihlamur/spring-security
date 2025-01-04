package dev.antozy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {

    @Schema(description = "Response status", example = "true")
    private boolean success;

    @Schema(description = "Response message", example = "Operation completed successfully")
    private String message;

    @Schema(description = "Response data object")
    private Object data;

    @Schema(description = "Error code (if any)", example = "ERR_400")
    private String errorCode;

    @Schema(description = "Detailed error description")
    private String details;

    @Schema(description = "List of validation errors")
    private List<String> errors;

    @Schema(description = "Transaction timestamp", example = "2024-01-20T10:15:30Z")
    private LocalDateTime timestamp;

    @Schema(description = "Transaction ID for tracking", example = "tx-123456")
    private String transactionId;

    public static ResponseDTO success(String message, Object data) {
        return ResponseDTO.builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .build();
    }

    public static ResponseDTO error(String message, String errorCode, String details) {
        return ResponseDTO.builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .details(details)
                .timestamp(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .build();
    }

    public static ResponseDTO validationError(String message, List<String> errors) {
        return ResponseDTO.builder()
                .success(false)
                .message(message)
                .errorCode("VALIDATION_ERROR")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .build();
    }
}