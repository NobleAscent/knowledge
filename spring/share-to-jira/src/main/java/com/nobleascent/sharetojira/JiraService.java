package com.nobleascent.sharetojira;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nobleascent.sharetojira.models.TicketRequest;
import com.nobleascent.sharetojira.models.iOSRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
@Slf4j
public class JiraService {
    private final String authHeader;

    private final RestClient restClient;

    private final PostClient postClient;

    private final JiraConfig jiraConfig;

    public JiraService(JiraConfig jiraConfig, PostClient postClient) {
        this.restClient = RestClient.create(jiraConfig.getJiraURL());
        this.authHeader = "Basic " + Base64.getEncoder().encodeToString((jiraConfig.getEmail() + ":" + jiraConfig.getApiToken()).getBytes());
        this.jiraConfig = jiraConfig;
        this.postClient = postClient;
    }

    public String createIssue(iOSRequest iOSRequest) throws JsonProcessingException {
        TicketRequest requestToJira = new TicketRequest(jiraConfig.getProjectKey(), iOSRequest.title(), iOSRequest.description());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(requestToJira);
        log.info(body);

        return postClient.getRestClient().post()
                .uri("/rest/api/3/issue")
                .header("Authorization", authHeader)
                .body(requestToJira)
                .retrieve()
                .body(String.class);
    }
}
