package com.springai.springai_proj_2.exception;



public class ChatResponseException extends RuntimeException{

    public ChatResponseException(){
        super("Exception occurred while get response from LLM");
    }
}
