package com.example.Xcrack.Service.Implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Model.UserHashtag;
import com.example.Xcrack.Repository.HashtagRepository;
import com.example.Xcrack.Repository.UserHashtagRepository;

import com.example.Xcrack.Service.UserHashtagService;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UserHashtagServiceImpl implements UserHashtagService{
    @Autowired
    private UserHashtagRepository userHashtagRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Transactional
    public void addInitialHashtagPreference(User user, String hashtagStr) {
        addOrUpdateUserHashtag(user, hashtagStr, 8);
    }

    @Transactional
    public void addUserHashtagFromFollowing(User user, String hashtagStr) {
        addOrUpdateUserHashtag(user, hashtagStr, 9);
    }

    @Transactional
    public void addUserHashtagFromPost(User user, String hashtagStr) {
        addOrUpdateUserHashtag(user, hashtagStr, 7);
    }

    @Transactional
    public void addUserHashtagFromReply(User user, String hashtagStr) {
        addOrUpdateUserHashtag(user, hashtagStr, 6);
    }

    @Transactional
    public void addUserHashtagFromLike(User user, String hashtagStr) {
        addOrUpdateUserHashtag(user, hashtagStr, 4);
    }

    private void addOrUpdateUserHashtag(User user, String hashtagStr, int valueToAdd) {
        Optional<UserHashtag> optionalUserHashtag = userHashtagRepository.findByUserUsernameAndHashtagHashtagIgnoreCase(user.getUsername(), hashtagStr);
        if (optionalUserHashtag.isPresent()) {
            UserHashtag userHashtag = optionalUserHashtag.get();
            userHashtag.setValue(userHashtag.getValue() + valueToAdd);
            userHashtagRepository.save(userHashtag);
        } else {
            Hashtag hashtag = hashtagRepository.findByHashtag(hashtagStr);
            if (hashtag == null) {
                hashtag = new Hashtag();
                hashtag.setHashtag(hashtagStr);
                hashtagRepository.save(hashtag);
            }
            UserHashtag userHashtag = new UserHashtag();
            userHashtag.setUser(user);
            userHashtag.setHashtag(hashtag);
            userHashtag.setValue(valueToAdd);
            userHashtagRepository.save(userHashtag);
        }
    }

    @Override
    @Transactional
    public void addUserHashtagsFromFollowing(User follower, User userToFollow) {
        List<UserHashtag> topHashtags = userHashtagRepository.findTop3ByUserOrderByValueDesc(userToFollow);
        for (UserHashtag hashtag : topHashtags) {
            UserHashtag newHashtag = new UserHashtag();
            newHashtag.setUser(follower);
            newHashtag.setHashtag(hashtag.getHashtag());
            newHashtag.setValue(6); // Assuming the initial value when following is 8
            userHashtagRepository.save(newHashtag);
        }
    }

    // @Transactional
    // public void addUserHashtagFromRetweet(User user, String hashtagStr) {
    //     addOrUpdateUserHashtag(user, hashtagStr, 5);
    // }

    // @Transactional
    // public void addUserHashtagFromBookmark(User user, String hashtagStr) {
    //     addOrUpdateUserHashtag(user, hashtagStr, 3);
    // }

    // @Transactional
    // public void addUserHashtagFromShare(User user, String hashtagStr) {
    //     addOrUpdateUserHashtag(user, hashtagStr, 2);
    // }

    // @Transactional
    // public void reduceUserHashtagValueFromDislike(User user, String hashtagStr) {
    //     Optional<UserHashtag> optionalUserHashtag = userHashtagRepository.findByUserUsernameAndHashtagHashtagIgnoreCase(user.getUsername(), hashtagStr);
    //     if (optionalUserHashtag.isPresent()) {
    //         UserHashtag userHashtag = optionalUserHashtag.get();
    //         userHashtag.setValue(userHashtag.getValue() - 5);
    //         userHashtagRepository.save(userHashtag);
    //     }
    //     else{}
    // }
}