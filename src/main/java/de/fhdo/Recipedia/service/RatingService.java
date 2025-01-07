package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.RatingDto;
import java.util.List;

public interface RatingService {
    RatingDto createRating(RatingDto ratingDto);
    Double getAverageRating(Long recipeId);
    List<RatingDto> getRatingsByRecipe(Long recipeId);
}
