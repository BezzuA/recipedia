package de.fhdo.Recipedia.resolver.input;

import lombok.Data;
import java.util.List;

@Data
public class RecipeInput {
    private String title;
    private String description;
    private String category;
    private List<String> ingredients;
    private String instructions;
    private Long authorId;
}