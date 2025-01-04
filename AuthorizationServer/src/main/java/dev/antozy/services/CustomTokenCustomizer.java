package dev.antozy.services;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

public interface CustomTokenCustomizer extends OAuth2TokenCustomizer<JwtEncodingContext> {

}
