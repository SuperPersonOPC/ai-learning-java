package com.superpersonopc.ragdemomodule.rag;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rag")
public class RagController {

    private final RagDemoService ragDemoService;

    public RagController(RagDemoService ragDemoService) {
        this.ragDemoService = ragDemoService;
    }

    @PostMapping
    public RagResponse ask(@Valid @RequestBody RagRequest request) {
        return ragDemoService.ask(request.question());
    }
}
