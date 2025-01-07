package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Rating}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto implements Serializable {
    private Long ratingId;
    private Integer score;
    private Long userId;
    private Long recipeId;
}