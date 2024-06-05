package com.example.Xcrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Service.PostService;
import com.example.Xcrack.Service.ReplyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/create-reply")
    public Reply createReply(@RequestBody Reply reply) {
        Reply saveReply = replyService.createReply(reply.getContent(), reply.getUsername(), reply.getParentPost().getId(), reply.getMediaList());        
        return saveReply;
    }

    
}
