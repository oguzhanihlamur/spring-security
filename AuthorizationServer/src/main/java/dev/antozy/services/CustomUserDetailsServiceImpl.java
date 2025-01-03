package dev.antozy.services;

import dev.antozy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findActiveByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .authorities(String.valueOf(user.getRoles()))
                        .roles(String.valueOf(user.getRoles()))
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
    }


}
