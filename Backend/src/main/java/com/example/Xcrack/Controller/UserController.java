package com.example.Xcrack.Controller;

import com.example.Xcrack.DTO.UserDetails;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserProfile(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    
    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }    

    @GetMapping("/{username}/details")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable String username) {
        UserDetails userDetails = userService.getUserDetailsByUsername(username);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/block/{usernameToBlock}")
    public ResponseEntity<Void> blockUser(@PathVariable String username, @PathVariable String usernameToBlock) {
        userService.blockUser(username, usernameToBlock);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{username}/unblock/{usernameToUnblock}")
    public ResponseEntity<Void> unblockUser(@PathVariable String username, @PathVariable String usernameToUnblock) {
        userService.unblockUser(username, usernameToUnblock);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/replies")
    public List<Reply> getReplies(@PathVariable String username) {
        return userService.getRepliesByUsername(username);
    }

    
    @PutMapping("/{username}/ban")
    public ResponseEntity<Void> banUser(@PathVariable String username) {
        userService.banUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{username}/unban")
    public ResponseEntity<Void> unbanUser(@PathVariable String username) {
        userService.unbanUser(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{username}/follow/{usernameToFollow}")
    public ResponseEntity<Void> FollowUser(@PathVariable String username, @PathVariable String usernameToFollow) {
        userService.followUser(username, usernameToFollow);
        return ResponseEntity.ok().build(); 
    }

    @PostMapping("/{username}/unfollow/{usernameToUnfollow}")
    public ResponseEntity<Void> unfollowUser(@PathVariable String username, @PathVariable String usernameToUnfollow) {
        userService.unfollowUser(username, usernameToUnfollow);
        return ResponseEntity.ok().build(); 
    }
    
    
}
