package com.example.websocket.service;

import com.example.websocket.dto.ChatMessageDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class StreamDataService {

    private final WebClient webClient;

    public StreamDataService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434/api").build();
    }

    public Flux<String> fetchStreamData(String jsonData) {

        log.info("fetchStreamData: {}", jsonData);

        return this.webClient
                .post()
                .uri("/generate")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .bodyValue(jsonData)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
