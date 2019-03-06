package com.endsound.trpg.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class DefualtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> body = ImmutableMap.of(
                "message", "無權限操作",
                "code", UUID.randomUUID(),
                "time", Timestamp.from(Instant.now())
        );


        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(new ObjectMapper().writeValueAsString(body));
        response.flushBuffer();
    }
}
