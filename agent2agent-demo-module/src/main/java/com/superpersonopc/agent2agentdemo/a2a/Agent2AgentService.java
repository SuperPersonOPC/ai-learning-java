package com.superpersonopc.agent2agentdemo.a2a;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class Agent2AgentService {

    private final ChatClient chatClient;

    public Agent2AgentService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Agent2AgentResponse runWorkflow(String goal) {
        String plannerOutput = plannerStep(goal);
        String writerOutput = writerStep(goal, plannerOutput);

        String finalAnswer = "Plan:\n" + plannerOutput + "\n\nDraft:\n" + writerOutput;

        return new Agent2AgentResponse(plannerOutput, writerOutput, finalAnswer);
    }

    private String plannerStep(String goal) {
        String plannerPrompt = """
                You are PlannerAgent.
                Produce a concise execution plan for the following goal.
                Return 3-5 bullet points only.

                Goal: %s
                """.formatted(goal);

        return chatClient.prompt(plannerPrompt)
                .call()
                .content();
    }

    private String writerStep(String goal, String plannerOutput) {
        String writerPrompt = """
                You are WriterAgent.
                Use the plan below to generate a short and practical answer.
                Keep it under 180 words.

                Goal: %s

                Plan:
                %s
                """.formatted(goal, plannerOutput);

        return chatClient.prompt(writerPrompt)
                .call()
                .content();
    }
}
