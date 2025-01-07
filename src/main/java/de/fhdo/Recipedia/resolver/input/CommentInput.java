package de.fhdo.Recipedia.resolver.input;

import lombok.Data;

@Data
public class CommentInput {
    private String text;
    private Long userId;
    private Long recipeId;
}