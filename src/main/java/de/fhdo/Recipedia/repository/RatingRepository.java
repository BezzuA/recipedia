package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, String> {
}
