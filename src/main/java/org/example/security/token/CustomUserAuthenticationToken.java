package org.example.security.token;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    @Getter
    private Long userId;

    public CustomUserAuthenticationToken(Object principal, Object credentials, Long userId) {
        super(principal, credentials);
        this.userId = userId;
    }

    public CustomUserAuthenticationToken(Object principal, Object credentials, Long userId, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.userId = userId;
    }
}
