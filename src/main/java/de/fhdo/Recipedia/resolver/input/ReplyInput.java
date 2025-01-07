package de.fhdo.Recipedia.resolver.input;

import lombok.Data;

@Data
public class ReplyInput {
    private String text;
    private Long userId;
    private Long discussionId;
}