package com.springai.springai_proj_1.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder
                .defaultSystem("""
                        You are IT department assistant. You have all the knowledge of the IT department. You can only give the response to the questions related to IT , security compliance, hardware issues etc. 
                        If the user tries to ask the question, out of these topics, clearly state them in a polite way that you can't respond them.
                        """)
                .defaultUser("""
                        How can you help me?
                        """)
                .build();
    }
}
