package com.endsound.trpg.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {
    public AuthenticationToken(String principal, String credentials){
        super(principal, credentials);
    }

    public AuthenticationToken(String principal, String credentials, List<? extends GrantedAuthority> authorities){
        super(principal, credentials, authorities);
    }
}
