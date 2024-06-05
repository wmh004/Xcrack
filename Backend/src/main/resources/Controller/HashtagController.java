package com.example.Xcrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Service.HashtagService;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    @PostMapping("/add")
    public Hashtag addHashtag(@RequestBody String hashtag) {
        return hashtagService.addHashtag(hashtag);
    }

    @GetMapping("/get-all")
    public List<Hashtag> getAllHashtags() {
        return hashtagService.getAllHashtags();
    }

}
