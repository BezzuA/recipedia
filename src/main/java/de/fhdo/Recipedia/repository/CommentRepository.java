package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Comment;
import de.fhdo.Recipedia.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipeOrderByCreationDateDesc(Recipe recipe);
}