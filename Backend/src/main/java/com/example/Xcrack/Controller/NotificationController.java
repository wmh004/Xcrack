package com.example.Xcrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Service.PostService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private PostService postService;

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable int postId) {
        return postService.getPostByPostID(postId);
    }
}
