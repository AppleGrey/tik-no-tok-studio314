package com.studio314.authservice.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;

public class DefaultAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {

    private final AuthenticationManager authenticationManager;

    public DefaultAuthenticationManagerResolver(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationManager resolve(HttpServletRequest context) {
        return authenticationManager;
    }
}
