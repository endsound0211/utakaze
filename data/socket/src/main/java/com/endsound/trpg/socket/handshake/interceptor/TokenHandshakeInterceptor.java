package com.endsound.trpg.socket.handshake.interceptor;

import com.endsound.trpg.security.bean.JwtPayload;
import com.endsound.trpg.security.component.JwtComponent;
import com.endsound.trpg.security.jooq.tables.pojos.User;
import com.endsound.trpg.socket.handshake.bean.TokenPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import java.util.Map;

public class TokenHandshakeInterceptor implements HandshakeInterceptor {
    private JwtComponent jwtComponent;

    public TokenHandshakeInterceptor(JwtComponent jwtComponent){
        this.jwtComponent = jwtComponent;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        String token = req.getServletRequest().getParameter("token");

        try {
            JwtPayload<User> jwtPayload = jwtComponent.parseToken(token);
            TokenPrincipal principal = new TokenPrincipal()
                    .setUser(jwtPayload.getData());

            attributes.put("principal", principal);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
