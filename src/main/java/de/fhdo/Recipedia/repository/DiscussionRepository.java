package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Discussion;
import de.fhdo.Recipedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findAllByOrderByCreationDateDesc();
    List<Discussion> findByUserOrderByCreationDateDesc(User user);
}