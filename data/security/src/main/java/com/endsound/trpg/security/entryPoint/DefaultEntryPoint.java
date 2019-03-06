package com.endsound.trpg.security.entryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class DefaultEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map body = ImmutableMap.of(
                "message", authException.getMessage(),
                "code", UUID.randomUUID(),
                "time", Timestamp.from(Instant.now())
        );


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(new ObjectMapper().writeValueAsString(body));
        response.flushBuffer();
    }
}
