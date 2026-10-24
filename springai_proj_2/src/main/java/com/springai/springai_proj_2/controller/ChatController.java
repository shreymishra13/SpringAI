package com.springai.springai_proj_2.controller;


import com.springai.springai_proj_2.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {


    private final ChatService chatService;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping()
    public ResponseEntity<?> getResponse(@RequestParam("query") String query){

        return ResponseEntity.ok(chatService.getResponse(query));
    }

    @GetMapping("/mcp")
    public ResponseEntity<?> performAction(@RequestParam("query") String query){

        return ResponseEntity.ok(chatService.performAction(query));
    }
}
