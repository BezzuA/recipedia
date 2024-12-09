package de.fhdo.Recipedia.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    private String commentId;

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors

    public Comment() {
        // Default constructor
    }

    // Getters and Setters

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
  
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                '}';
        // Note: Avoid including relationships to prevent infinite recursion
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return commentId != null ? commentId.equals(comment.commentId) : comment.commentId == null;
    }

    @Override
    public int hashCode() {
        return commentId != null ? commentId.hashCode() : 0;
    }
}
