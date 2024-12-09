package de.fhdo.Recipedia.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Rating {

    @Id
    private String ratingId;

    private Integer score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors

    public Rating() {
        // Default constructor
    }

    // Getters and Setters

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Optional: Override toString(), equals(), and hashCode()

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId='" + ratingId + '\'' +
                ", score=" + score +
                ", date=" + date +
                '}';
        // Note: Avoid including relationships to prevent infinite recursion
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        return ratingId != null ? ratingId.equals(rating.ratingId) : rating.ratingId == null;
    }

    @Override
    public int hashCode() {
        return ratingId != null ? ratingId.hashCode() : 0;
    }
}
