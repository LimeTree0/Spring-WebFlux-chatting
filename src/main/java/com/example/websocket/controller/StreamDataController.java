package com.example.websocket.controller;

import com.example.websocket.dto.ChatMessageDto;
import com.example.websocket.service.StreamDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StreamDataController {

    private final StreamDataService streamDataService;


    @PostMapping("/fetch")
    public Flux<String> fetchStreamData(@RequestBody String json) {

        log.info("fetchStreamData: {}", json);

        return streamDataService.fetchStreamData(json);
    }

}
