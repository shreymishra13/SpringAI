package com.springai.springai_proj_1.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;


@Slf4j
public class TokenAuditAdvisor implements CallAdvisor {


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {



        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        Usage usage = chatClientResponse.chatResponse().getMetadata().getUsage();

        log.debug(usage.toString());


        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "TokenAuditAdvisor";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
