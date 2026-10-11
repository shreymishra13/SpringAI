package com.springai.springai_proj_1.config;


import com.springai.springai_proj_1.advisor.TokenAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

//    Chat Options - Helps in customizing the ChatClient
//    model - Find the correct model
//            frequencyPenalty = repeting certain words
//            presencePenalty = controls mentioning new topics
//    temperature = creativity
//            topP = controls the randomness
//    stopSequences = stop generation once sudden specific phrase
//            maxTokens = max number of tokens allowed in response
//            topK = controls how many top choices are considered.


    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        ChatOptions.Builder options= OllamaChatOptions.builder()
                .model(OllamaModel.CODELLAMA)
                .temperature(0.7);
//                .maxTokens(200);

        List<String> stringList = List.of("Sex", "sexual");
        return builder
                .defaultOptions(options)
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
