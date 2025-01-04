package dev.antozy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisteredClientRequestDTO {

    private String clientId;
    private String clientName;
    private String secret;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private Set<String> authenticationMethods;
    private Set<String> grantTypes;
    private Long userId;
    private Set<Long> roleIds;

}
