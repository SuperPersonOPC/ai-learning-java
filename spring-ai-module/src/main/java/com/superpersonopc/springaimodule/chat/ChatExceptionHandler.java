package com.superpersonopc.springaimodule.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChatExceptionHandler {

    @ExceptionHandler(AiClientUnavailableException.class)
    public ResponseEntity<ChatErrorResponse> handleAiClientUnavailable(AiClientUnavailableException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ChatErrorResponse("AI_CLIENT_UNAVAILABLE", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ChatErrorResponse> handleValidationError(MethodArgumentNotValidException exception) {
        String message = "Invalid request payload";
        if (exception.getBindingResult().hasFieldErrors()) {
            message = exception.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        }
        return ResponseEntity.badRequest()
                .body(new ChatErrorResponse("VALIDATION_ERROR", message));
    }
}
