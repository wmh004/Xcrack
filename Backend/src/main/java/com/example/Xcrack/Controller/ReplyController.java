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

    @PostMapping("/{parentPostId}/create-reply")
    public Reply createReply(@RequestBody Reply reply, @PathVariable int parentPostId) {
        return replyService.createReply(reply.getContent(), reply.getUsername(), parentPostId);
    }

    @PostMapping("/{replyId}/remove-reply")
    public void removeReply(@PathVariable int replyId) {
        replyService.removeReply(replyId);
    }

    @GetMapping("/byusername/{username}")
    public List<Reply> getRepliesByUsername(@PathVariable String username) {
        return replyService.getRepliesByUsername(username);
    }

    @PostMapping("/{replyId}/like")
    public ResponseEntity<?> likeReply(@PathVariable int replyId, @RequestBody LikeRequest likeRequest) {
        replyService.likeReply(replyId, likeRequest.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byid/{replyId}")
    public Reply getReplyById(@PathVariable int replyId) {
        return replyService.getReplyById(replyId);
    }
    
    @GetMapping("/byparentid/{parentId}")
    public List<Reply> getRepliesByParentId(@PathVariable int parentId) {
        return replyService.getRepliesByParentId(parentId);
    }
}

