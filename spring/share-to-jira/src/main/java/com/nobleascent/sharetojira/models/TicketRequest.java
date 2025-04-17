package com.nobleascent.sharetojira.models;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@lombok.Value
public class TicketRequest {
    String projectKey;

    @NotBlank(message = "Summary is required")
    String summary;

    JiraDescription description;

    IssueType issueType = IssueType.STORY;

    public TicketRequest(String projectKey, String summary, String description) {
        this.summary = summary;
        this.projectKey = projectKey;

        DescriptionText descriptionText = new DescriptionText(description);
        JiraDescriptionParagraph jiraDescriptionParagraph = new JiraDescriptionParagraph(List.of(descriptionText));
        JiraDescription jiraDescription = new JiraDescription(List.of(jiraDescriptionParagraph));

        this.description = jiraDescription;
    }
}

@lombok.Value
class JiraDescription {
    String type = "doc";
    int version = 1;
    List<JiraDescriptionParagraph> content;
}

@lombok.Value
class JiraDescriptionParagraph {
    String type = "paragraph";
    List<DescriptionText> content;
}

@lombok.Value
class DescriptionText {
    String type = "text";
    String text;
}

@Getter
enum IssueType {
    TASK("10001"),
    STORY("10006");

    private final String id;

    IssueType(String id) {
        this.id = id;
    }
}