package com.springai.springai_proj_1.tools;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Slf4j
@Component
public class WeatherTool {


    @Tool(name = "getWeather", description = "These tools get the  current time of the user's timezone")
    public String getWeather(){
        log.info("Calling weather tool call");
        return LocalDateTime.now().toString();
    }
}
