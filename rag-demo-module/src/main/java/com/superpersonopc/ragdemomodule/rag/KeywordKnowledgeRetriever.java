package com.superpersonopc.ragdemomodule.rag;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class KeywordKnowledgeRetriever {

    private static final Pattern TOKEN_SPLIT = Pattern.compile("[^a-z0-9]+", Pattern.CASE_INSENSITIVE);

    private static final List<KnowledgeDocument> KNOWLEDGE_BASE = List.of(
            new KnowledgeDocument(
                    "spring-ai-overview",
                    "Spring AI Overview",
                    "Spring AI provides a Spring-friendly abstraction over AI providers and offers a ChatClient API for building prompts and handling model responses."
            ),
            new KnowledgeDocument(
                    "rag-definition",
                    "What RAG Means",
                    "Retrieval-Augmented Generation combines retrieval from an external knowledge source with generation from a language model to improve factual grounding."
            ),
            new KnowledgeDocument(
                    "rag-workflow",
                    "RAG Workflow",
                    "A simple RAG flow is: accept a user question, retrieve the most relevant passages, build a grounded prompt, and ask the model to answer using that context."
            ),
            new KnowledgeDocument(
                    "vector-stores",
                    "Vector Stores",
                    "In production systems, retrieval is commonly backed by embeddings and vector databases, while demos can start with keyword retrieval over a small in-memory corpus."
            ),
            new KnowledgeDocument(
                    "hallucination-control",
                    "Controlling Hallucination",
                    "To reduce hallucinations, instruct the model to rely on provided context and explicitly acknowledge when information is unavailable in retrieved documents."
            )
    );

    public List<KnowledgeDocument> retrieve(String query, int limit) {
        Set<String> queryTokens = tokenize(query);

        return KNOWLEDGE_BASE.stream()
                .map(document -> new ScoredDocument(document, score(document, queryTokens)))
                .filter(scored -> scored.score() > 0)
                .sorted(Comparator.comparingInt(ScoredDocument::score).reversed())
                .limit(Math.max(1, limit))
                .map(ScoredDocument::document)
                .toList();
    }

    private int score(KnowledgeDocument document, Set<String> queryTokens) {
        if (queryTokens.isEmpty()) {
            return 0;
        }

        Set<String> documentTokens = tokenize(document.title() + " " + document.content());
        int overlap = 0;
        for (String queryToken : queryTokens) {
            if (documentTokens.contains(queryToken)) {
                overlap++;
            }
        }
        return overlap;
    }

    private Set<String> tokenize(String text) {
        String normalized = text == null ? "" : text.toLowerCase();
        String[] rawTokens = TOKEN_SPLIT.split(normalized);
        Set<String> cleaned = new LinkedHashSet<>();
        for (String token : rawTokens) {
            if (token.length() >= 2) {
                cleaned.add(token);
            }
        }
        return Set.copyOf(cleaned);
    }

    private record ScoredDocument(KnowledgeDocument document, int score) {
    }
}
