package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Xcrack.Model.FollowingStatus;
import com.example.Xcrack.Model.User;
import java.util.List;


public interface FollowingStatusRepository extends JpaRepository<FollowingStatus, Integer> {
    List<FollowingStatus> findByFollower(User follower);
    
}