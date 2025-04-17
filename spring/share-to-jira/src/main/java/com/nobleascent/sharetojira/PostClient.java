package com.nobleascent.sharetojira;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class PostClient {
    @Getter
    private final RestClient restClient;

    private final JiraConfig jiraConfig;

    public PostClient(RestClient.Builder builder, JiraConfig jiraConfig) {
        this.restClient = builder
                .baseUrl(jiraConfig.getJiraURL())
                .requestInterceptor((request, body, execution) -> {
                    logRequest(request, body);
                    var response = execution.execute(request, body);
                    logResponse(request, response);
                    return response;
                })
                .build();
        this.jiraConfig = jiraConfig;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.info("Request: {} {}", request.getMethod(), request.getURI());
        log.info(String.valueOf(request.getHeaders()));
        if (body != null && body.length > 0) {
            log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        }
    }

    private void logResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
        log.info("Response status: {}", response.getStatusCode());
        log.info(String.valueOf(response.getHeaders()));
        byte[] responseBody = response.getBody().readAllBytes();
        if (responseBody.length > 0) {
            log.info("Response body: {}", new String(responseBody, StandardCharsets.UTF_8));
        }
    }
}