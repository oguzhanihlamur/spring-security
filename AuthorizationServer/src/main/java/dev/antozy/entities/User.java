package dev.antozy.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user")
    @Nullable
    private Set<RegisteredClientRecord> clients = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}