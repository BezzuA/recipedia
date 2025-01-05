package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DiscussionController {
    private final DiscussionService discussionService;
    private final ReplyService replyService;

    public DiscussionController(DiscussionService discussionService,
                                ReplyService replyService) {
        this.discussionService = discussionService;
        this.replyService = replyService;
    }

    @GetMapping("/discussions")
    public String getDiscussions(Model model) {
        List<DiscussionDto> discussions = discussionService.getDiscussions();

        model.addAttribute("discussions", discussions);

        System.out.println(discussions.get(0));

        return "discussions";
    }

    @GetMapping("/discussion/{id}")
    public String getDiscussionDetails(Model model, @PathVariable Long id) {
        DiscussionDto discussion = discussionService.getDiscussion(id);
        List<ReplyDto> replies = replyService.getRepliesByDiscussion(id);

        model.addAttribute("discussion", discussion);
        model.addAttribute("replies", replies);

        return "discussion_details";
    }
}
