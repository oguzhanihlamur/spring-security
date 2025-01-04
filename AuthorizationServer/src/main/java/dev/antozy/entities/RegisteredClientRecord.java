package dev.antozy.entities;

import dev.antozy.conventers.JsonConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REGISTERED_CLIENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisteredClientRecord extends BaseEntity {
    @Id
    private String id;
    private String clientId;
    private String clientName;
    private String secret;

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "CLOB")
    private Set<String> redirectUris = new HashSet<>();

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "CLOB")
    private Set<String> scopes = new HashSet<>();

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "CLOB")
    private Set<String> authenticationMethods = new HashSet<>();

    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "CLOB")
    private Set<String> grantTypes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nullable
    private User user;

    @ManyToMany
    @JoinTable(
            name = "CLIENT_ROLES",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}