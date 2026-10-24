package com.springai.springai_proj_1.rag;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HRPolicyLoader {

    private final VectorStore vectorStore;

    @Value("classpath:/pdf_resource/HR Policy - Shrey Corp.pdf")
    Resource hrPolicyPDF;

    public HRPolicyLoader(VectorStore vectorStore){
        this.vectorStore = vectorStore;

    }

    @PostConstruct
    public void hrPolicyLoader(){
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(hrPolicyPDF);
        List<Document> documents = tikaDocumentReader.get();

        TextSplitter textSplitter = TokenTextSplitter.builder()
                .withChunkSize(100)
                .withMaxNumChunks(500)
                .build();

        vectorStore.add(textSplitter.split(documents));

    }
}
