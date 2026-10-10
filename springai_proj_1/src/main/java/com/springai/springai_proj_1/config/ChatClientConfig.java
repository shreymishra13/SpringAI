package com.springai.springai_proj_1.config;


import com.springai.springai_proj_1.advisor.TokenAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        List<String> stringList = List.of("Sex", "sexual");
        return builder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(stringList),
                        new TokenAuditAdvisor()))
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
