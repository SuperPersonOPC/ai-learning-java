package com.superpersonopc.agent2agentdemo.a2a;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class Agent2AgentServiceTest {

    @Test
    void shouldChainPlannerAndWriterOutputs() {
        ChatClient.Builder builder = mock(ChatClient.Builder.class);
        ChatClient chatClient = mock(ChatClient.class);
        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        ChatClient.CallResponseSpec callResponseSpec = mock(ChatClient.CallResponseSpec.class);

        when(builder.build()).thenReturn(chatClient);
        when(chatClient.prompt(org.mockito.ArgumentMatchers.anyString())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(callResponseSpec);
        when(callResponseSpec.content()).thenReturn("step-1 plan", "step-2 draft");

        Agent2AgentService service = new Agent2AgentService(builder);

        Agent2AgentResponse response = service.runWorkflow("Prepare demo");

        assertThat(response.plannerOutput()).isEqualTo("step-1 plan");
        assertThat(response.writerOutput()).isEqualTo("step-2 draft");
        assertThat(response.finalAnswer()).contains("step-1 plan").contains("step-2 draft");

        verify(chatClient, times(2)).prompt(org.mockito.ArgumentMatchers.anyString());
    }
}
