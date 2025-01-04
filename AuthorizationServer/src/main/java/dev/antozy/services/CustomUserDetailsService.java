package dev.antozy.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface CustomUserDetailsService extends UserDetailsService {

    Set<String> getUserRoles(Long id);

}
