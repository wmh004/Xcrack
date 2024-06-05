package com.example.Xcrack.Repository;

import com.example.Xcrack.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    public User findByUsername(String username); 
    public User findByEmail(String email); 
    public User findById(int ID); 
    public User findByForgotPasswordCode(String forgotPasswordCode);
    public boolean existsByUsername(String username); 
    public boolean existsByEmail(String email); 
    
    // Custom query to find all users blocked by a specific user
    @Query("SELECT u.blockedUsers FROM User u WHERE u.username = :username")
    List<User> findBlockedUsersByUsername(@Param("username") String username);
    
}