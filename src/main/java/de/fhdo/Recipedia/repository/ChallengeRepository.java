package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Challenge;
import de.fhdo.Recipedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findAllByOrderByEndDateDesc();

    @Query("SELECT c FROM Challenge c JOIN c.users u WHERE u.userId = :userId ORDER BY c.endDate ASC")
    List<Challenge> findChallengesByUserIdOrderByEndDateAsc(@Param("userId") Long userId);
}