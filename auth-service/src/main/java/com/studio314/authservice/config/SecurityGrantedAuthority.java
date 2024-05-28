package com.studio314.authservice.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityGrantedAuthority implements GrantedAuthority, Serializable {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
