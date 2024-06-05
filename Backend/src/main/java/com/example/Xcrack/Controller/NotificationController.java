package com.example.Xcrack.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Notification;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Service.NotificationService;
import com.example.Xcrack.Service.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable int postId) {
        return postService.getPostByPostID(postId);
    }
}
