package de.fhdo.Recipedia.resolver.input;

import lombok.Data;

import java.util.Date;

@Data
public class ChallengeInput {
    private String title;
    private String description;
    private String startDate;
    private String endDate;
}