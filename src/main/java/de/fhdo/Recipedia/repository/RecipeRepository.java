package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(String category);
    List<Recipe> findByAuthor(User author);

    List<Recipe> findByTitleContainingIgnoreCase(String keyword);

}