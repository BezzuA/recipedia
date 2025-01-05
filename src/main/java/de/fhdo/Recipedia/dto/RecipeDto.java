package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Recipe}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto implements Serializable {
    private Long recipeId;
    private String title;
    private String description;
    private String category;
    private Long viewCount;
    private List<String> ingredients;
    private String instructions;
    private Date creationDate;
    private UserDto author = new UserDto();
    private double averageRating;
    private Boolean isRated;
}