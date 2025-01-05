package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Reply}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto implements Serializable {
    private Long replyId;
    private String text;
    private Date creationDate;
    private UserDto user = new UserDto();
    private Long discussionId;
}