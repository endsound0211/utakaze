package com.endsound.trpg.socket;

import com.endsound.trpg.security.component.JwtComponent;
import com.endsound.trpg.socket.handshake.handler.TokenHandshakeHandler;
import com.endsound.trpg.socket.handshake.interceptor.TokenHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@ConditionalOnWebApplication
@EnableWebSocketMessageBroker
public class SocketConfiguration{

    @Configuration
    @ConditionalOnWebApplication
    public static class MessageBroker implements WebSocketMessageBrokerConfigurer {
        @Autowired
        private HandshakeInterceptor tokenHandshakeInterceptor;
        @Autowired
        private HandshakeHandler tokenHandshakeHandler;

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/socket")
                    .addInterceptors(tokenHandshakeInterceptor)
                    .setHandshakeHandler(tokenHandshakeHandler)
                    .setAllowedOrigins("*")
                    .withSockJS();
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
        }

    }

    @Bean
    public HandshakeInterceptor tokenHandshakeInterceptor(JwtComponent jwtComponent){
        return new TokenHandshakeInterceptor(jwtComponent);
    }

    @Bean
    public HandshakeHandler tokenHandshakeHandler(){
        return new TokenHandshakeHandler();
    }
}
