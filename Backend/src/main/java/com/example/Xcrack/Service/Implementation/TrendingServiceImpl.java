
package com.example.Xcrack.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Xcrack.Repository.TrendingHashtagRepository;
import com.example.Xcrack.Repository.PostRepository;
import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Service.TrendingService;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class TrendingServiceImpl implements TrendingService {

    @Autowired
    private TrendingHashtagRepository hashtagRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Hashtag> getTopTrendingHashtags(int limit) {
        return hashtagRepository.findTopTrendingHashtags(limit);


}
     @Override
    public void addPost(Post post) {
        for (Hashtag hashtag : post.getHashtags()) {
            Hashtag existingHashtag = hashtagRepository.findByHashtag(hashtag.getHashtag());
            if (existingHashtag != null) {
                existingHashtag.incrementCount();
                hashtagRepository.save(existingHashtag);
            } else {
                hashtag.setCount(1);
                hashtagRepository.save(hashtag);
            }
        }
        postRepository.save(post);
    }
}