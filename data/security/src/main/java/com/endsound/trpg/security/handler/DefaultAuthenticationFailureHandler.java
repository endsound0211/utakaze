package com.endsound.trpg.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private AuthenticationEntryPoint authenticationEntryPoint;

    public DefaultAuthenticationFailureHandler(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        authenticationEntryPoint.commence(request, response, exception);
    }
}
