package dev.antozy.controllers;

import dev.antozy.dto.RegisteredClientRequestDTO;
import dev.antozy.dto.ResponseDTO;
import dev.antozy.services.RegisteredClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/registered-client", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "oauth2")
public class RegisteredClientController {

    private final RegisteredClientService registeredClientService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('CLIENT_CREATOR')")
    @Operation(summary = "Register new OAuth2 client", description = "Creates a new OAuth2 client and returns registration client details", security = @SecurityRequirement(name = "bearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client successfully registered with generated credentials", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters or client configuration"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "Required resources not found for client registration"),
            @ApiResponse(responseCode = "500", description = "Server error occurred during client registration")
    })
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisteredClientRequestDTO request) {
        try {
            return Optional.ofNullable(registeredClientService.createRegisteredClient(request))
                    .map(client -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(ResponseDTO.success("Client registered successfully", client)))
                    .orElse(ResponseEntity.badRequest()
                            .body(ResponseDTO.error(
                                    "Registration failed",
                                    "ERR_400",
                                    "Invalid client configuration")));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest()
                    .body(ResponseDTO.validationError("Validation failed", List.of(String.valueOf(e.getCause()))));
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ResponseDTO.error(
                            "Registration failed",
                            "ERR_409",
                            "Client already exists"));
        } catch (Exception e) {
            log.error("Error during client registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.error(
                            e.getMessage(),
                            "ERR_500",
                            e.getCause() != null ? e.getCause().getMessage() : "Please check log files."));
        }
    }

}
