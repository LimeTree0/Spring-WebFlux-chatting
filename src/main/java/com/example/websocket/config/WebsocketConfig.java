//package com.example.websocket.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.HandlerMapping;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.reactive.socket.server.WebSocketService;
//import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
//import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//import java.util.Map;
//
//@Configuration
//@EnableWebFlux
//public class WebsocketConfig {
//
//    @Bean
//    public HandlerMapping webSocketMapping() {
//        return new SimpleUrlHandlerMapping(Map.of("/ws/numbers", webSocketHandler()), 10);
//    }
//
//    @Bean
//    public WebSocketHandler webSocketHandler() {
//        return session -> {
//            Flux<String> flux = Flux.interval(Duration.ofSeconds(1))
//                    .map(Object::toString);
//            return session.send(flux.map(session::textMessage));
//        };
//    }
//
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter(new WebSocketService(new ReactorNettyRequestUpgradeStrategy()));
//    }
//}
