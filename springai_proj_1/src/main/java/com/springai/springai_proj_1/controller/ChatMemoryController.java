package com.springai.springai_proj_1.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-memory")
public class ChatMemoryController {

    private final ChatClient chatClient;

    public ChatMemoryController(ChatClient.Builder builder, ChatMemory chatMemory){
        this.chatClient = builder
                .defaultAdvisors(
                        List.of(new SimpleLoggerAdvisor() , MessageChatMemoryAdvisor.builder(chatMemory).build())
                )
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> chat(@PathVariable String id, @RequestParam String message){
        String response = chatClient.prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID,id))
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }
}
