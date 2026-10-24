package com.springai.springai_proj_2.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ChatResponseException.class)
    public ResponseEntity<?> chatResponseHandler(){
        return ResponseEntity.badRequest().body("Got Some exception. Contact Admin or mail us at help@shreymishra.com");
    }
}
