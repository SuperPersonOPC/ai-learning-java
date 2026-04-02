package com.superpersonopc.agent2agentdemo.a2a;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agent2agent")
public class Agent2AgentController {

    private final Agent2AgentService agent2AgentService;

    public Agent2AgentController(Agent2AgentService agent2AgentService) {
        this.agent2AgentService = agent2AgentService;
    }

    @PostMapping
    public Agent2AgentResponse run(@Valid @RequestBody Agent2AgentRequest request) {
        return agent2AgentService.runWorkflow(request.goal());
    }
}
