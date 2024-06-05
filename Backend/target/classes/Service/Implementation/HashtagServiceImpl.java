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
        Hashtag existingHashtag = hashtagRepository.findByHashtag(hashtag);
        if (getAllHashtags().contains(existingHashtag)) {
            return null;
        } else {
            Hashtag newHashtag = new Hashtag();
            newHashtag.setHashtag(hashtag);
            return hashtagRepository.save(newHashtag);
        }
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        return hashtagRepository.findAll();
    }

}
