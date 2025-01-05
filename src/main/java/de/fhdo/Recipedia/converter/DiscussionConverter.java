package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.entity.Discussion;
import org.springframework.stereotype.Component;

@Component
public class DiscussionConverter {

    public DiscussionDto toDto(Discussion discussion) {
        DiscussionDto discussionDto = new DiscussionDto();

        discussionDto.setDiscussionId(discussion.getDiscussionId());
        discussionDto.setTitle(discussion.getTitle());
        discussionDto.setDescription(discussion.getDescription());
        discussion.setCreationDate(discussion.getCreationDate());
        discussionDto.setRepliesCount(discussion.getReplies().size());

        discussionDto.getUser().setUserId(discussion.getUser().getUserId());
        discussionDto.getUser().setUsername(discussion.getUser().getUsername());

        return discussionDto;
    }


    public Discussion toEntity(DiscussionDto discussionDto) {
        Discussion discussion = new Discussion();

        discussion.setTitle(discussionDto.getTitle());
        discussion.setDescription(discussionDto.getDescription());

        return discussion;
    }
}
