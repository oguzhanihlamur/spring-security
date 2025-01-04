package dev.antozy.services;

import dev.antozy.dto.RegisteredClientRequestDTO;
import dev.antozy.entities.RegisteredClientRecord;
import dev.antozy.repositories.RegisteredClientRecordRepository;
import dev.antozy.repositories.RoleRepository;
import dev.antozy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisteredClientServiceImpl implements RegisteredClientService {

    private final RegisteredClientRecordRepository registeredClientRecordRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public RegisteredClientRecord createRegisteredClient(RegisteredClientRequestDTO registeredClientRequestDTO) {

        RegisteredClientRecord registeredClientRecord = RegisteredClientRecord.builder()
                .id(UUID.randomUUID().toString())
                .clientId(registeredClientRequestDTO.getClientId())
                .clientName(registeredClientRequestDTO.getClientName())
                .secret(registeredClientRequestDTO.getSecret())
                .user(userRepository.findById(registeredClientRequestDTO.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found")))
                .scopes(registeredClientRequestDTO.getScopes())
                .authenticationMethods(registeredClientRequestDTO.getAuthenticationMethods())
                .grantTypes(registeredClientRequestDTO.getGrantTypes())
                .roles(roleRepository.findByIdIn(registeredClientRequestDTO.getRoleIds()))
                .redirectUris(registeredClientRequestDTO.getRedirectUris())
                .build();

        return registeredClientRecordRepository.save(registeredClientRecord);
    }
}
