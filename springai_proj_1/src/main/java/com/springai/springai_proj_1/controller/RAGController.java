package com.springai.springai_proj_1.controller;


import com.springai.springai_proj_1.advisor.TokenAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/promptTemplate/systemPromptRandomDataTemplate.st")
    Resource promptTemplateForRandomData;


    @Value("classpath:/promptTemplate/HrPolicySystemPromptTemplate.st")
    Resource promptTemplateForHRPolicy;

    public RAGController(ChatClient.Builder builder , VectorStore vectorStore){
        this.vectorStore = vectorStore;
        System.out.println("VectorStore implementation: "

                + vectorStore.getClass().getName());
        this.chatClient = builder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor() , new TokenAuditAdvisor()))
                .build();
    }

    @GetMapping("/chat")
    public ResponseEntity<?> ragChat(@RequestHeader("username") String userName,
                                     @RequestParam("message") String message){

        List<Document> documents = vectorStore.similaritySearch(message);

        String similarDocsString = documents.stream()
                .map(Document::getText).
                collect(Collectors.joining(System.lineSeparator()));

        String response = this.chatClient.prompt()
                .system(promptSystemSpec -> promptSystemSpec
                        .text(promptTemplateForRandomData)
                        .param("documents", similarDocsString))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/chat/hrpolicy")
    public ResponseEntity<?> hrPolicyQuery(@RequestHeader("username") String userName,
                                     @RequestParam("message") String message){


        SearchRequest searchRequest = SearchRequest.builder()
                .query(message)
                .topK(3)
                .similarityThreshold(0.5)
                .build();


        List<Document> documents = vectorStore.similaritySearch(searchRequest);

        String similarDocsString = documents.stream()
                .map(Document::getText).
                collect(Collectors.joining(System.lineSeparator()));

        String response = this.chatClient.prompt()
                .system(promptSystemSpec ->
                    promptSystemSpec
                            .text(promptTemplateForHRPolicy)
                            .param("documents", similarDocsString))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok().body(response);
    }
}
