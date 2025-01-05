package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Rating;
import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
  Rating findByUserAndRecipe(User user, Recipe recipe);
}