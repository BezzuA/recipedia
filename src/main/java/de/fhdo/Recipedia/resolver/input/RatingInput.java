package de.fhdo.Recipedia.resolver.input;

import lombok.Data;

@Data
public class RatingInput {
    private Long recipeId;
    private Long userId;
    private Integer score;
}