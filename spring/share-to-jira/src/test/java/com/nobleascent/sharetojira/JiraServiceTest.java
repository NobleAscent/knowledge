package com.nobleascent.sharetojira;

import com.nobleascent.sharetojira.models.TicketRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class JiraServiceTest {

    private JiraService jiraService;
    private MockRestServiceServer mockServer;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        jiraService = new JiraService() {
            public RestTemplate getRestTemplate() {
                return restTemplate;
            }
        };

        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void createIssue_shouldReturnResponseBody() {
        TicketRequest request = new TicketRequest();
        request.setSummary("Test ticket");
        request.setDescription("Description here");
        request.setIssueType("Task");

        String responseJson = "{ \"key\": \"TEST-101\" }";

        mockServer.expect(once(), requestTo("https://example.atlassian.net/rest/api/3/issue"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(responseJson, MediaType.APPLICATION_JSON));

        String result = jiraService.createIssue("TEST", request);
        assertThat(result).contains("TEST-101");

        mockServer.verify();
    }
}
