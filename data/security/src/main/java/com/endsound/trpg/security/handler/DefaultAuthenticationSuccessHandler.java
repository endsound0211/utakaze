package com.endsound.trpg.security.handler;

import com.endsound.trpg.security.bean.DefaultUserDetail;
import com.endsound.trpg.security.bean.JwtPayload;
import com.endsound.trpg.security.component.JwtComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private JwtComponent jwtComponent;

    public DefaultAuthenticationSuccessHandler(JwtComponent jwtComponent){
        this.jwtComponent = jwtComponent;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        DefaultUserDetail userDetail = (DefaultUserDetail) authentication.getDetails();

        //set jwt info
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setSubject(userDetail.getUsername())
                .setIssuer(request.getServerName())
                .setAudience(request.getHeader("origin"))
                .setData(userDetail.getData())
                .setRoles(userDetail.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        Map body = ImmutableMap.of(
                "token", jwtComponent.generateToken(jwtPayload)
        );

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(new ObjectMapper().writeValueAsString(body));
        response.flushBuffer();
    }
}
