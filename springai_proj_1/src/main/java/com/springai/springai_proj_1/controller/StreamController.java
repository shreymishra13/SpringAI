package com.springai.springai_proj_1.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class StreamController {

     private ChatClient chatClient;

    StreamController(ChatClient chatClient){
        this.chatClient = chatClient;

    }


    @GetMapping("/stream")
    public Flux<String> stream(@RequestParam String message){
        return this.chatClient
                .prompt()
                .user(message)
                .stream()
                .content();
    }

}
