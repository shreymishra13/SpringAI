package com.springai.springai_proj_1.controller;


import com.springai.springai_proj_1.advisor.TokenAuditAdvisor;
import com.springai.springai_proj_1.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tool")
public class ToolCallController {

    private final ChatClient chatClient;

    public ToolCallController(ChatClient.Builder builder, WeatherTool weatherTool){
        this.chatClient = builder
                .defaultTools(weatherTool)
                .defaultAdvisors(
                        List.of(new SimpleLoggerAdvisor() , new TokenAuditAdvisor())
                )
                .build();
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam("query") String query){
        String content = this.chatClient.prompt()
                .system("You are advised to response the data along with the name of the city or the zone that you have considered the current zone˛")
                .user(query)
                .call()
                .content();

       return ResponseEntity.ok(content);

    }
}
