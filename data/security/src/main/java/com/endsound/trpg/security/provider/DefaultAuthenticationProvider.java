package com.endsound.trpg.security.provider;

import com.endsound.trpg.security.bean.DefaultUserDetail;
import com.endsound.trpg.security.token.AuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class DefaultAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    public DefaultAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken token = (AuthenticationToken) authentication;
        String username = Optional.of(token.getPrincipal())
                .map(Object::toString)
                .filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new UsernameNotFoundException("帳號密碼錯誤"));

        String password = Optional.of(token.getCredentials())
                .map(Object::toString)
                .filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new BadCredentialsException("帳號密碼錯誤"));

        try {
            password = Base64.getEncoder()
                    .encodeToString(MessageDigest.getInstance("MD5").digest(password.getBytes()));
        }catch (NoSuchAlgorithmException e) {
            throw new BadCredentialsException("帳號密碼錯誤");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(!userDetails.isEnabled())
            throw new DisabledException("帳號已停用");

        if(!userDetails.isAccountNonLocked())
            throw new LockedException("帳號已被鎖");

        if(!userDetails.isAccountNonExpired())
            throw new AccountExpiredException("帳號過期");

        if(!userDetails.isCredentialsNonExpired())
            throw new CredentialsExpiredException("密碼過期");

        if(!userDetails.getPassword().equals(password))
            throw new BadCredentialsException("帳號密碼錯誤");

        AuthenticationToken authenticationToken = new AuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                (List<? extends GrantedAuthority>) userDetails.getAuthorities()
        );


        authenticationToken.setDetails(userDetails);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AuthenticationToken.class);
    }
}
