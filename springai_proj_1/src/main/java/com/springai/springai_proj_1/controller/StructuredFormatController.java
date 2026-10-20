package com.springai.springai_proj_1.controller;

import com.springai.springai_proj_1.dto.CountryCitiesResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StructuredFormatController {

    private ChatClient chatClient;

    StructuredFormatController(ChatClient.Builder builder){
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/structured")
    public ResponseEntity<?> getData(@RequestParam("message") String message){
        return ResponseEntity.ok().body(
                this.chatClient
                        .prompt()
                        .user(message)
                        .call()
                        .entity(CountryCitiesResponseDTO.class)

        );

    }

    @GetMapping("/structured-list")
    public ResponseEntity<List<String>> getListData(@RequestParam("message") String message){
        return ResponseEntity.ok().body(
                this.chatClient
                        .prompt()
                        .user(message)
                        .call()
                        .entity(new ListOutputConverter())


        );

    }
}
