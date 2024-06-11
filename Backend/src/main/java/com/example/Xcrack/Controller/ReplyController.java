package com.example.Xcrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Xcrack.DTO.LikeRequest;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/{username}/create-reply")
    public Reply createReply(@RequestBody Reply reply, @PathVariable String username) {
        return replyService.createReply(reply.getContent(), username, reply.getParentPost().getId());
    }

    @PostMapping("/{postId}/remove-reply")
    public void removeReply(@PathVariable int replyId) {
        replyService.removeReply(replyId);
    }

    @GetMapping("/{username}")
    public List<Reply> getRepliesByUsername(@PathVariable String username) {
        return replyService.getRepliesByUsername(username);
    }

    @PostMapping("/{replyId}/like")
    public ResponseEntity<?> likeReply(@PathVariable int replyId, @RequestBody LikeRequest likeRequest) {
        replyService.likeReply(replyId, likeRequest.getUserId());
        return ResponseEntity.ok().build();
    }
}

