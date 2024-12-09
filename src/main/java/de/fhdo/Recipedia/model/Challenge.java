package de.fhdo.Recipedia.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Challenge {

    @Id
    private String challengeId;

    private String title;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Relationships
    @OneToMany(mappedBy = "challenge")
    private List<Recipe> recipes;

    @ManyToMany(mappedBy = "challenges")
    private List<User> users;

    // Constructors

    public Challenge() {
        // Default constructor
    }

    // Getters and Setters

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
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

    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
 
    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
 
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<User> getUsers() {
        return users;
    }
 
    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Optional: Override toString(), equals(), and hashCode()

    @Override
    public String toString() {
        return "Challenge{" +
                "challengeId='" + challengeId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
        // Note: Avoid including collections in toString() to prevent excessive output
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        return challengeId != null ? challengeId.equals(challenge.challengeId) : challenge.challengeId == null;
    }

    @Override
    public int hashCode() {
        return challengeId != null ? challengeId.hashCode() : 0;
    }
}
