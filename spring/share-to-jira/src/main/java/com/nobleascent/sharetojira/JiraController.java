package com.nobleascent.sharetojira;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nobleascent.sharetojira.models.iOSRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jira")
public class JiraController {

    private final JiraService jiraService;

    public JiraController(JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @PostMapping("/create")
    public String createTicket(@Valid @RequestBody iOSRequest request) throws JsonProcessingException {
        return jiraService.createIssue(request);
    }
}
