package com.example.Xcrack.Service.Implementation;

import com.example.Xcrack.Exception.NotFoundException;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) { 
        return userRepository.existsByEmail(email);
    }

    @Override
    public void banUser(int ID) {
        User userToBan = userRepository.findById(ID);
        if (userToBan != null) {
            userToBan.ban();
            userRepository.save(userToBan);
        }
    }

    @Override
    public void unbanUser(int ID) {
            User userToUnban = userRepository.findById(ID);
        if (userToUnban != null) {
            userToUnban.unban();
            userRepository.save(userToUnban);
        }
    }

    @Override
    public void blockUser(String username, String usernameToBlock) {
        User user = userRepository.findByUsername(username);
        User userToBlock = userRepository.findByUsername(usernameToBlock);

        if (user == null)
            throw new NotFoundException("User not found: " + username);

        if (userToBlock == null)
            throw new NotFoundException("User to block not found: " + usernameToBlock);

        if (user != null && userToBlock != null) {
            user.blockUser(userToBlock);
            userRepository.save(user);
        }
    }

    @Override
    public void unblockUser(String username, String usernameToUnblock) {
        User user = userRepository.findByUsername(username);
        User userToUnblock = userRepository.findByUsername(usernameToUnblock);

        if (user == null)
            throw new NotFoundException("User not found: " + username);

        if (userToUnblock == null)
            throw new NotFoundException("User to unblock not found: " + usernameToUnblock);

        if (user != null && userToUnblock != null) {
            user.unblockUser(userToUnblock);
            userRepository.save(user);
        }
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.findById(id);

        if (user == null)
            throw new NotFoundException("User not found with ID: " + id);
        
        return user;
    }

    @Override
    public List<Reply> getRepliesByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user == null)
            throw new NotFoundException("User not found with username: " + username);

        List<Reply> replies = user.getReplies();
        
        return replies;
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user == null)
            throw new NotFoundException("User not found with username: " + username);

        List<Post> posts = user.getPosts();
        
        return posts;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new NotFoundException("User not found with username: " + username);

        return user;
    }

    @Override
    public UserDetailsDTO getUserDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found with username: " + username);
        }
        return new UserDetailsDTO(user.getUsername(), user.getName(), user.getBio(), user.getDob());
    }
    
}