package com.endsound.trpg.security.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends GenericFilterBean {
    private AuthenticationEntryPoint authenticationEntryPoint;

    public ExceptionFilter(AuthenticationEntryPoint authenticationEntryPoint){
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request, response);
        }catch (AuthenticationException e){
            authenticationEntryPoint.commence((HttpServletRequest) request, (HttpServletResponse) response, e);
        }
    }
}
