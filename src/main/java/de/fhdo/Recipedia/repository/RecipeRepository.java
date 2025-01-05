package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategoryOrderByCreationDateDesc(String category);
    List<Recipe> findByAuthorOrderByCreationDateDesc(User author);
    List<Recipe> findByTitleContainingIgnoreCase(String keyword);
    List<Recipe> findAllByOrderByViewCountDesc();

}