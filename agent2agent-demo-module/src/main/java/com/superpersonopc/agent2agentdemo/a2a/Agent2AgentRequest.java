package com.superpersonopc.agent2agentdemo.a2a;

import jakarta.validation.constraints.NotBlank;

public record Agent2AgentRequest(
        @NotBlank(message = "goal must not be blank") String goal
) {
}
