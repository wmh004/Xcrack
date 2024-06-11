package com.example.Xcrack.Service;

import com.example.Xcrack.Model.FollowingStatus;
import com.example.Xcrack.Model.User;

public interface FollowingStatusService {
    FollowingStatus FollowUser(User follower, User following);
    FollowingStatus UnfollowUser(User follower, User following);
}
