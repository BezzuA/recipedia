package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.RatingDto;

public interface RatingService {
    RatingDto addRating(RatingDto ratingDto);
    Double getAverageRating(Long recipeId);
}
