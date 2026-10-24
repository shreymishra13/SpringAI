package com.springai.springai_proj_2.service;


import com.springai.springai_proj_2.exception.ChatResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatService {
    private final ChatClient chatClient;

    ChatService(ChatClient.Builder builder){
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
    public String getResponse(String query) {
        log.debug("Query asked :{}", query);

        return this.chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }
}
