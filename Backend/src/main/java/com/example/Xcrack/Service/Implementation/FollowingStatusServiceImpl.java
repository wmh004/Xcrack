package com.example.Xcrack.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Exception.NotFoundException;
import com.example.Xcrack.Model.FollowingStatus;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.FollowingStatusRepository;
import com.example.Xcrack.Service.FollowingStatusService;

@Service
public class FollowingStatusServiceImpl implements FollowingStatusService {

    @Autowired
    FollowingStatusRepository followingStatusRepository;

    public FollowingStatus FollowUser(User follower, User following) {
        List<FollowingStatus> FollowStatus = followingStatusRepository.findByFollower(follower);

        if (FollowStatus == null) {
            FollowingStatus followingStatus = new FollowingStatus(follower, following, 1);
            return followingStatusRepository.save(followingStatus);

        } else {
            for (FollowingStatus followingStatus : FollowStatus) {

                if (followingStatus.getFollowing().equals(following)) {
                    followingStatus.setStatus(1);
                    return followingStatusRepository.save(followingStatus);
                }
            }

            FollowingStatus newfollowingStatus = new FollowingStatus(follower, following, 1);
            return followingStatusRepository.save(newfollowingStatus);
        }
    }


    public FollowingStatus UnfollowUser(User follower, User following) {

        List<FollowingStatus> FollowStatus = followingStatusRepository.findByFollower(follower);

        if (FollowStatus == null) {
            throw new NotFoundException("You do not follow this person.");

        } else {
            for (FollowingStatus followingStatus : FollowStatus) {

                if (followingStatus.getFollowing().equals(following)) {
                    followingStatus.setStatus(-1);
                    return followingStatusRepository.save(followingStatus);
                }
            }

            throw new NotFoundException("You do not follow this person.");
        }
    }
}
