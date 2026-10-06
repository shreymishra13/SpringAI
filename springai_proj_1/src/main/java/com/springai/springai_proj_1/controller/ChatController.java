package com.springai.springai_proj_1.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {
    
    private final ChatClient chatClient;
    
    public ChatController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }
    
    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody String question){

        String content = this.chatClient.prompt(question).call().content();

        return ResponseEntity.ok().body(
                "Here is your answer provided by Ollama : \n"+ content
        );
    }
}
