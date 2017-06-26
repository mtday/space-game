package com.mday.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.annotation.Nonnull;

/**
 * Provides the configuration for web sockets.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(@Nonnull final MessageBrokerRegistry messageBrokerRegistry) {
        messageBrokerRegistry.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(@Nonnull final StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/stomp").withSockJS();
    }
}
