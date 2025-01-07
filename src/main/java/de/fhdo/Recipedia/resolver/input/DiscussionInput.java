package de.fhdo.Recipedia.resolver.input;

import lombok.Data;

@Data
public class DiscussionInput {
    private String title;
    private String description;
    private Long userId;
}