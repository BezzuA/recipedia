package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, String> {
}

