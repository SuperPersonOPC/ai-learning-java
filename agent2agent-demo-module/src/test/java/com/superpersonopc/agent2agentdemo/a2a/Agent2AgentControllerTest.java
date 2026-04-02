package com.superpersonopc.agent2agentdemo.a2a;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Agent2AgentController.class)
class Agent2AgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Agent2AgentService agent2AgentService;

    @Test
    void shouldReturnWorkflowResponse() throws Exception {
        given(agent2AgentService.runWorkflow(eq("Create a quick launch plan")))
                .willReturn(new Agent2AgentResponse("plan", "draft", "final"));

        mockMvc.perform(post("/api/v1/agent2agent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "goal": "Create a quick launch plan"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plannerOutput").value("plan"))
                .andExpect(jsonPath("$.writerOutput").value("draft"))
                .andExpect(jsonPath("$.finalAnswer").value("final"));
    }

    @Test
    void shouldRejectBlankGoal() throws Exception {
        mockMvc.perform(post("/api/v1/agent2agent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "goal": ""
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}
