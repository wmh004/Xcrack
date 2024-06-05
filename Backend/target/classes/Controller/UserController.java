package com.example.Xcrack.Controller;

import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Xcrack.Service.Implementation.UserDetailsDTO;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

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
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable String username) {
        UserDetailsDTO userDetails = userService.getUserDetailsByUsername(username);
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
    
}
