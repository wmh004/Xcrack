package com.example.Xcrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Service.TrendingService;
import java.util.List;


@RestController
@RequestMapping("/api/trending")
public class TrendingController {

    @Autowired
    private TrendingService trendingService;

    @GetMapping("/hashtags")
    public ResponseEntity<List<Hashtag>> getTopTrendingHashtags(@RequestParam(defaultValue = "10") int limit) {
        List<Hashtag> trendingHashtags = trendingService.getTopTrendingHashtags(limit);
        return new ResponseEntity<>(trendingHashtags, HttpStatus.OK);
    }
}

