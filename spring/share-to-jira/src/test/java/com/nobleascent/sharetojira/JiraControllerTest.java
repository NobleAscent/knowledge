package com.nobleascent.sharetojira;

import com.nobleascent.sharetojira.models.TicketRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JiraControllerTest {

    private JiraService jiraService;
    private JiraController controller;

    @BeforeEach
    void setUp() {
        jiraService = mock(JiraService.class);
        controller = new JiraController(jiraService, "TEST");
    }

    @Test
    void createTicket_shouldReturnIssueKey() {
        TicketRequest request = new TicketRequest();
        request.setSummary("Test Summary");
        request.setDescription("Test Description");
        request.setIssueType("Bug");

        when(jiraService.createIssue("TEST", request)).thenReturn("JIRA-123");

        String response = controller.createTicket(request);

        assertThat(response).isEqualTo("JIRA-123");
        verify(jiraService, times(1)).createIssue("TEST", request);
    }
}
