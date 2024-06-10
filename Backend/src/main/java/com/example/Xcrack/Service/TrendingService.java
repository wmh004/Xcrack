package com.example.Xcrack.Service;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Post;

import java.util.List;

public interface TrendingService {
    List<Hashtag> getTopTrendingHashtags(int limit);
    void addPost(Post post);
}