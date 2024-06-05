package com.example.Xcrack.Service;

import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.Implementation.UserDetailsDTO;
import java.util.List;

public interface UserService {

    List<User> getAllUser();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<Reply> getRepliesByUsername(String username);
    List<Post> getPostsByUsername(String username);
    void banUser(int ID);
    void unbanUser(int ID);
    void blockUser(String username, String usernameToBlock);
    void unblockUser(String username, String usernameToUnblock);
    UserDetailsDTO getUserDetailsByUsername(String username);

}