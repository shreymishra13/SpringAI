package com.springai.springai_proj_1.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {


    @GetMapping("/say-hello")
    public ResponseEntity<Object> sayHello(){
        return ResponseEntity.ok().body("Hello World!");
    }
}
