package com.superpersonopc.ragdemomodule.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagDemoService {

    private static final int RETRIEVAL_LIMIT = 3;

    private final ChatClient chatClient;
    private final KeywordKnowledgeRetriever retriever;

    public RagDemoService(ChatClient.Builder chatClientBuilder, KeywordKnowledgeRetriever retriever) {
        this.chatClient = chatClientBuilder.build();
        this.retriever = retriever;
    }

    public RagResponse ask(String question) {
        List<KnowledgeDocument> contextDocs = retriever.retrieve(question, RETRIEVAL_LIMIT);
        String prompt = buildPrompt(question, contextDocs);

        String answer = chatClient.prompt(prompt)
                .call()
                .content();

        List<String> sources = contextDocs.stream()
                .map(KnowledgeDocument::title)
                .toList();

        return new RagResponse(answer, sources);
    }

    private String buildPrompt(String question, List<KnowledgeDocument> contextDocs) {
        String contextBlock;
        if (contextDocs.isEmpty()) {
            contextBlock = "No relevant context was retrieved.";
        } else {
            contextBlock = contextDocs.stream()
                    .map(doc -> "- [" + doc.id() + "] " + doc.title() + ": " + doc.content())
                    .collect(Collectors.joining("\n"));
        }

        return """
                You are a helpful assistant answering with retrieval-augmented context.
                Use only the provided context to answer. If the answer is not in context, say so clearly.

                Retrieved context:
                %s

                User question:
                %s
                """.formatted(contextBlock, question);
    }
}
