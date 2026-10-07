package com.springai.springai_proj_1.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class ChatController {
    
    private final ChatClient chatClient;
    
    public ChatController(ChatClient chatClient){
        this.chatClient = chatClient;

    }
    
    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody String question){
        log.info("Question received : "+ question);
        String content = null;
        if(question == null || question.isEmpty()){
            content = this.chatClient
                    .prompt()
                    .call()
                    .content();
            return ResponseEntity.ok().body(content);
        }

        content= this.chatClient
                .prompt()
//                .system("""
//                        You are IT department assistant. You have all the knowledge of the IT department. You can only give the response to the questions related to IT , security compliance, hardware issues etc.
//                        If the user tries to ask the question, out of these topics, clearly state them in a polite way that you can't respond them.
//                        """)
//                .system("""
//                        You can override the default behavior and work on it here.
//                        """)
                .user(question)
                .call().content();

//        String content = this.chatClient.prompt(question).call().content();
        log.info("Response received from the LLM : "+ content);
        return ResponseEntity.ok().body(
                "Here is your answer provided by Ollama : \n"+ content
        );
    }
}
