package de.fhdo.Recipedia.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Recipe {

    @Id
    private String recipeId;

    private String title;
    private String description;

    @ElementCollection
    private List<String> ingredients;

    private String instructions;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;

    @OneToMany(mappedBy = "recipe")
    private List<Rating> ratings;

    // Constructors

    public Recipe() {
        // Default constructor
    }

    // Getters and Setters

    public String getRecipeId() {
        return recipeId;
    }
 
    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
 
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
 
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Date getCreationDate() {
        return creationDate;
    }
 
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }
 
    public void setAuthor(User author) {
        this.author = author;
    }

    public Challenge getChallenge() {
        return challenge;
    }
 
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public List<Comment> getComments() {
        return comments;
    }
 
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
 
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    // Optional: Override toString(), equals(), and hashCode()

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId='" + recipeId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
        // Note: Avoid including collections and relationships in toString() to prevent infinite recursion
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return recipeId != null ? recipeId.equals(recipe.recipeId) : recipe.recipeId == null;
    }

    @Override
    public int hashCode() {
        return recipeId != null ? recipeId.hashCode() : 0;
    }
}
