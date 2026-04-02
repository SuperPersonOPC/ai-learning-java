package com.superpersonopc.springaimodule.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    private static final String DISABLED_API_KEY = "disabled";

    private final ChatClient chatClient;
    private final String configuredApiKey;

    public AiChatService(ChatClient.Builder builder, @Value("${spring.ai.openai.api-key:disabled}") String configuredApiKey) {
        this.chatClient = builder.build();
        this.configuredApiKey = configuredApiKey;
    }

    public String chat(String prompt) {
        if (DISABLED_API_KEY.equalsIgnoreCase(configuredApiKey)) {
            throw new AiClientUnavailableException(
                    "OpenAI API key is not configured. Set OPENAI_API_KEY to enable chat.");
        }
        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}
