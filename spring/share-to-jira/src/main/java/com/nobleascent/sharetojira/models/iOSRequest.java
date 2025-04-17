package com.nobleascent.sharetojira.models;

import jakarta.validation.constraints.NotBlank;

public record iOSRequest(
        @NotBlank(message = "Ticket title is required") String title,
        @NotBlank(message = "Cannot create JIRA Tasks without extra information") String description
) {
}