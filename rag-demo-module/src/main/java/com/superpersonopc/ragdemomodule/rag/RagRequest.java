package com.superpersonopc.ragdemomodule.rag;

import jakarta.validation.constraints.NotBlank;

public record RagRequest(@NotBlank(message = "question must not be blank") String question) {
}
