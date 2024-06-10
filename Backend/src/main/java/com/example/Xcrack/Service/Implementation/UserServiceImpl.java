package com.example.Xcrack.Service.Implementation;

import com.example.Xcrack.DTO.UserDetails;
import com.example.Xcrack.Exception.UserNotFoundException;
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
    public void banUser(String username) {
        User userToBan = userRepository.findByUsername(username);
        if (userToBan != null) {
            userToBan.ban();
            userRepository.save(userToBan);
        }
    }

    @Override
    public void unbanUser(String username) {
        User userToUnban = userRepository.findByUsername(username);
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
            throw new UserNotFoundException(username);

        if (userToBlock == null)
            throw new UserNotFoundException(usernameToBlock);

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
            throw new UserNotFoundException(username);

        if (userToUnblock == null)
            throw new UserNotFoundException(usernameToUnblock);

        if (user != null && userToUnblock != null) {
            user.unblockUser(userToUnblock);
            userRepository.save(user);
        }
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.findById(id);

        if (user == null)
            throw new UserNotFoundException(id);
        
        return user;
    }

    @Override
    public List<Reply> getRepliesByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user == null)
            throw new UserNotFoundException(username);

        List<Reply> replies = user.getReplies();
        
        return replies;
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        
        if (user == null)
            throw new UserNotFoundException(username);

        List<Post> posts = user.getPosts();
        
        return posts;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UserNotFoundException(username);

        return user;
    }

    @Override
    public void followUser(String username, String usernameToFollow) {
        User user = userRepository.findByUsername(username);
        User userToFollow = userRepository.findByUsername(usernameToFollow);

        if (user == null)
            throw new UserNotFoundException(username);

        if (userToFollow == null)
            throw new UserNotFoundException(usernameToFollow);

        user.followUser(userToFollow);
    }

    @Override
    public void unfollowUser(String username, String usernameToUnfollow) {
        User user = userRepository.findByUsername(username);
        User userToUnfollow = userRepository.findByUsername(usernameToUnfollow);

        if (user == null)
            throw new UserNotFoundException(username);

        if (userToUnfollow == null)
            throw new UserNotFoundException(usernameToUnfollow);

        user.unfollowUser(userToUnfollow);
    }

    @Override
    public UserDetails getUserDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return new UserDetails(user.getUsername(), user.getName(), user.getBio(), user.getProfilePictureUrl(), user.getBackgroundPictureUrl(), user.getLocation(), user.getWebsiteUrl(), user.getDob(), user.getFollowers(), user.getFollowing(), user.getPosts(), user.getReplies());
    }
    
}