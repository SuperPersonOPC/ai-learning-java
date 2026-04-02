package com.superpersonopc.agent2agentdemo.a2a;

public record Agent2AgentResponse(
        String plannerOutput,
        String writerOutput,
        String finalAnswer
) {
}
