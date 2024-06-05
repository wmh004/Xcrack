package com.example.Xcrack.Controller;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Service.RecommendService;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;
    

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam int userId) {
        return recommendService.getPosts(userId);
    }
}