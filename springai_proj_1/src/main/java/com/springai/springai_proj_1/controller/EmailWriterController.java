package com.springai.springai_proj_1.controller;


import com.springai.springai_proj_1.dto.EmailRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmailWriterController {

    private ChatClient chatClient;

    @Value("classpath:/promptTemplate/emailGenerationPromptTemplate.st")
    Resource emailGenerationPromptTemplate;

    public EmailWriterController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @PostMapping("/email")
    public ResponseEntity<?> emailWriter(@RequestBody EmailRequestDTO emailRequestDTO){

        log.info("Email Request DTO received : "+ emailRequestDTO);
        String content = chatClient
                .prompt()
                .system("""
                        You are a professional email writer who helps the user in generating the professional email for the customer support team.
                        """)
                .user(promptUserSpec ->
                        promptUserSpec.text(emailGenerationPromptTemplate)
                                .param("customerName", emailRequestDTO.getCustomerName())
                                .param("customerMsg", emailRequestDTO.getCustomerMsg())
                        )
                .call()
                .content();

        log.info("Response received from LLM : "+ content);

        return ResponseEntity.ok().body(content);




    }
}
