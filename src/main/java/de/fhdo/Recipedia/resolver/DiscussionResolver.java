package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.resolver.input.DiscussionInput;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.ReplyService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DiscussionResolver {
    private final DiscussionService discussionService;
    private final ReplyService replyService;

    public DiscussionResolver(DiscussionService discussionService, ReplyService replyService) {
        this.discussionService = discussionService;
        this.replyService = replyService;
    }

    @QueryMapping
    public DiscussionDto discussion(@Argument Long id) {
        return discussionService.getDiscussion(id);
    }

    @QueryMapping
    public List<DiscussionDto> discussions() {
        return discussionService.getDiscussions();
    }

    @QueryMapping
    public List<DiscussionDto> discussionsByUser(@Argument Long userId) {
        return discussionService.getDiscussionsByUser(userId);
    }

    @QueryMapping
    public List<ReplyDto> repliesByDiscussion(@Argument Long discussionId) {
        return replyService.getRepliesByDiscussion(discussionId);
    }

    @MutationMapping
    public DiscussionDto createDiscussion(@Argument DiscussionInput discussion) {
        DiscussionDto discussionDto = convertInputToDto(discussion);
        return discussionService.createDiscussion(discussionDto);
    }

    @MutationMapping
    public Boolean deleteDiscussion(@Argument Long discussionId) {
        return discussionService.deleteDiscussion(discussionId);
    }

    @SchemaMapping(typeName = "Discussion")
    public List<ReplyDto> replies(DiscussionDto discussion) {
        return replyService.getRepliesByDiscussion(discussion.getDiscussionId());
    }

    private DiscussionDto convertInputToDto(DiscussionInput input) {
        DiscussionDto dto = new DiscussionDto();
        dto.setTitle(input.getTitle());
        dto.setDescription(input.getDescription());
        dto.getUser().setUserId(input.getUserId());
        return dto;
    }
} 