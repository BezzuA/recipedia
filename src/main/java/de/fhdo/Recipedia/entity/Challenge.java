package de.fhdo.Recipedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "challenges")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;

    private String title;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Relationships
    @OneToMany(mappedBy = "challenge", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Recipe> recipes = new ArrayList<>();

    @ManyToMany(mappedBy = "challenges")
    private List<User> users = new ArrayList<>();
}
