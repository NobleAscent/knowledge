package com.nobleascent.sharetojira;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Getter
public class JiraConfig {
    @Value("${jira.url}")
    private String jiraURL;

    @Value("${jira.email}")
    private String email;

    @Value("${jira.apiToken}")
    private String apiToken;

    @Value("${jira.projectKey}")
    private String projectKey;
}
