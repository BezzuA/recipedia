package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}