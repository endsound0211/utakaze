package com.endsound.trpg.security.filter;

import com.endsound.trpg.security.token.AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public AuthenticationFilter(){
        super(new AntPathRequestMatcher("/oauth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = Optional.ofNullable(request.getParameter("username")).orElse("");
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");

        return getAuthenticationManager().authenticate(new AuthenticationToken(username, password));
    }
}
