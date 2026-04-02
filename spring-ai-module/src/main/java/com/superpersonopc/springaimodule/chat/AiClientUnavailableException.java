package com.superpersonopc.springaimodule.chat;

public class AiClientUnavailableException extends RuntimeException {
    public AiClientUnavailableException(String message) {
        super(message);
    }
}
