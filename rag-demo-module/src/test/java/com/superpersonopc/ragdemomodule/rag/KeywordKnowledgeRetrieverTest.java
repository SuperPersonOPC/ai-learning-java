package com.superpersonopc.ragdemomodule.rag;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordKnowledgeRetrieverTest {

    private final KeywordKnowledgeRetriever retriever = new KeywordKnowledgeRetriever();

    @Test
    void retrievesTopDocumentsForMatchingQuery() {
        List<KnowledgeDocument> results = retriever.retrieve("How does retrieval augmented generation work?", 3);

        assertThat(results)
                .isNotEmpty()
                .extracting(KnowledgeDocument::id)
                .contains("rag-definition");
    }

    @Test
    void returnsEmptyWhenNoKeywordsMatch() {
        List<KnowledgeDocument> results = retriever.retrieve("quantum entanglement and fermions", 3);

        assertThat(results).isEmpty();
    }
}
