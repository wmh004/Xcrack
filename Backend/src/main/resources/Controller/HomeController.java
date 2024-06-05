package com.example.Xcrack.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Message;
import com.example.Xcrack.Model.Notification;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.MessageService;
import com.example.Xcrack.Service.NotificationService;
import com.example.Xcrack.Service.PostService;
import com.example.Xcrack.Service.RecommendService;
import com.example.Xcrack.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping
public class HomeController {

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public Map<String, List<Post>> getHomePosts(@RequestBody int userId) {
        List<Post> forYouPosts = recommendService.getPosts(userId);
        Map<String, List<Post>> response = new HashMap<>();
        response.put("forYou", forYouPosts);
        return response;
    }

    @GetMapping("/notifications")
    public List<Notification> getHomeNotifications(@RequestBody String username) {
        return notificationService.getAllNotificationsByUsername(username);
    }
    
    @GetMapping("/messages")
    public List<Message> getHomeMessages(@RequestBody int userId) {
        return messageService.getMessagesSentByUser(userService.getUserById(userId));
    }
    
    @GetMapping("/{username}")
    public User getHomeProfile(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/search")
    public List<Post> findPosts(
        @RequestParam(required = false) String content, 
        @RequestParam(required = false) List<String> hashtags) {
        List<Post> posts = postService.searchPosts(content, hashtags);
        return posts;
    }


}