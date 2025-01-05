package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.dto.RatingDto;
import de.fhdo.Recipedia.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter {

    public RatingDto toDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();

        ratingDto.setRatingId(rating.getRatingId());
        ratingDto.setScore(rating.getScore());
        ratingDto.setUserId(rating.getUser().getUserId());
        ratingDto.setRecipeId(rating.getRecipe().getRecipeId());

        return ratingDto;
    }


    public Rating toEntity(RatingDto ratingDto) {
        Rating rating = new Rating();

        rating.setScore(ratingDto.getScore());

        return rating;
    }
}
