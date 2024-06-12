package com.example.Xcrack.Service;

import com.example.Xcrack.DTO.UserDetails;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<Reply> getRepliesByUsername(String username);
    List<Post> getPostsByUsername(String username);
    void banUser(String username);
    void unbanUser(String username);
    void blockUser(String username, String usernameToBlock);
    void unblockUser(String username, String usernameToUnblock);
    void followUser(String username, String usernameToFollow);
    void unfollowUser(String username, String usernameToUnfollow);
    UserDetails getUserDetailsByUsername(String username);
    String getProfilePictureUrl(String username);

}