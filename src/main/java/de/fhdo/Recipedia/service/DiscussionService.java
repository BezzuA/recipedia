package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.DiscussionDto;

import java.util.List;

public interface DiscussionService {
    DiscussionDto addDiscussion(DiscussionDto discussionDto);
    Boolean deleteDiscussion(Long discussionId);
    DiscussionDto getDiscussion(Long discussionId);
    List<DiscussionDto> getDiscussions();
}
