package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Discussion}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionDto implements Serializable {
    private Long discussionId;
    private String title;
    private String description;
    private Date creationDate;
    private UserDto user = new UserDto();
    private Integer repliesCount;
}