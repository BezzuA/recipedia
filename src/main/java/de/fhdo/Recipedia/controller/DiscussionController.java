package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;
    private final ReplyService replyService;

    public DiscussionController(DiscussionService discussionService,
                                ReplyService replyService) {
        this.discussionService = discussionService;
        this.replyService = replyService;
    }

    @PostMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<DiscussionDto> createDiscussion(@RequestBody DiscussionDto discussionDto) {
        DiscussionDto createdDiscussion = discussionService.createDiscussion(discussionDto);
        if (createdDiscussion != null) {
            return new ResponseEntity<>(createdDiscussion, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{discussionId}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long discussionId) {
        Boolean success = discussionService.deleteDiscussion(discussionId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(
        path = "/{discussionId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<DiscussionDto> getDiscussion(@PathVariable Long discussionId) {
        DiscussionDto discussion = discussionService.getDiscussion(discussionId);
        if (discussion != null) {
            return new ResponseEntity<>(discussion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<DiscussionDto>> getAllDiscussions() {
        List<DiscussionDto> discussions = discussionService.getDiscussions();
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{discussionId}/replies",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<ReplyDto>> getRepliesByDiscussion(@PathVariable Long discussionId) {
        List<ReplyDto> replies = replyService.getRepliesByDiscussion(discussionId);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

}
