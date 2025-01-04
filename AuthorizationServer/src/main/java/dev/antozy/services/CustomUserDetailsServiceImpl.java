package dev.antozy.services;

import dev.antozy.entities.Role;
import dev.antozy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findActiveByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .authorities(getAuthorities(user.getRoles()))
                        .roles(String.valueOf(user.getRoles()))
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
    }

    @Override
    public Set<String> getUserRoles(Long id) {
        return userRepository.findById(id)
                .map(user -> user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }

}
