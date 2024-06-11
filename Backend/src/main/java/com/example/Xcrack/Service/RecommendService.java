package com.example.Xcrack.Service;

import java.util.List;
import com.example.Xcrack.Model.Post;

public interface RecommendService {
    List<Post> getPosts(String username);
}