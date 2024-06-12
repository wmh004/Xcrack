
package com.example.Xcrack.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Xcrack.Repository.TrendingHashtagRepository;
import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Service.TrendingService;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class TrendingServiceImpl implements TrendingService {

    @Autowired
    private TrendingHashtagRepository hashtagRepository;

    @Override
    public List<Hashtag> getTopTrendingHashtags(int limit) {
        return hashtagRepository.findTopTrendingHashtags(limit);
    }

}