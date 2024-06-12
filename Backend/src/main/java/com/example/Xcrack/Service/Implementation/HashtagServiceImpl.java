package com.example.Xcrack.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Repository.HashtagRepository;
import com.example.Xcrack.Service.HashtagService;

@Service
public class HashtagServiceImpl implements HashtagService{

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public Hashtag addHashtag(String hashtag) {
        // Check if the hashtag already exists
        Hashtag existingHashtag = hashtagRepository.findByHashtag(hashtag);
        if (existingHashtag != null) {
            // If it exists, increment the count
            existingHashtag.incrementCount();
            return hashtagRepository.save(existingHashtag); // Save the updated hashtag
        } else {
            // If it doesn't exist, create a new hashtag with count 1
            Hashtag newHashtag = new Hashtag();
            newHashtag.setHashtag(hashtag);
            newHashtag.setCount(1); // Set count to 1 for new hashtags
            // Save the new hashtag to the repository
            return hashtagRepository.save(newHashtag);
        }
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        return hashtagRepository.findAll();
    }

}
