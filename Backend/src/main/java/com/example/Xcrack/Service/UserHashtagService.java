package com.example.Xcrack.Service;

import com.example.Xcrack.Model.User;

public interface UserHashtagService {
    void addInitialHashtagPreference(User user, String hashtagStr);
    void addUserHashtagFromPost(User user, String hashtagStr);
    void addUserHashtagFromReply(User user, String hashtagStr);
    void addUserHashtagFromLike(User user, String hashtagStr);
    // void addUserHashtagsFromFollowing(User follower, User userToFollow);
    // void addUserHashtagFromFollowing(User user, String hashtagStr);
    // void addUserHashtagFromBookmark(User user, String hashtagStr);
    // void addUserHashtagFromShare(User user, String hashtagStr);
    // void addUserHashtagFromRetweet(User user, String hashtagStr);
}