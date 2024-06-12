package com.example.Xcrack.Service;

import com.example.Xcrack.Model.Hashtag;

import java.util.List;

public interface TrendingService {
    List<Hashtag> getTopTrendingHashtags(int limit);

}