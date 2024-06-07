package com.example.Xcrack.Exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("User not found with username: " + username);
    }

    public UserNotFoundException(int userId) {
        super("User not found with userId: " + userId);
    }
}
