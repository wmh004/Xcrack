package com.example.Xcrack.Service.Implementation;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Model.FollowingStatus;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.ReadPost;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Model.UserHashtag;
import com.example.Xcrack.Repository.FollowingStatusRepository;
import com.example.Xcrack.Repository.PostRepository;
import com.example.Xcrack.Repository.ReadPostRepository;
import com.example.Xcrack.Repository.UserHashtagRepository;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadPostRepository readPostRepository;

    @Autowired
    private FollowingStatusRepository followingStatusRepository;

    @Autowired
    private UserHashtagRepository userHashtagRepository;

    public List<Post> getPosts(int userId) {
        List<Post> posts = postRepository.findAll();
        User user = userRepository.findById(userId);
        List<ReadPost> readpost = readPostRepository.findByUser(user);
        List<User> blockedUsers = userRepository.findBlockedUsersByUsername(user.getUsername());
        List<FollowingStatus> followStatus = followingStatusRepository.findByFollower(user);
        List<UserHashtag> userHashtags = userHashtagRepository.findByUser(user);

        Iterator<Post> iterator = posts.iterator();

        // First loop to remove all the posts from banned users, read post, blocked user and unfollowed user -
        // then adds the value of each hashtag 
        while (iterator.hasNext()) { 
            boolean RPValue = false; // flag to continue to the next post if the post has been read (ReadPostValue)
            boolean BUValue = false; // flag to continue to the next post if the post is from a blocked uer (BlockedUserValue)
            boolean FSValue = false; // flag to continue to the next post if the post is from an unfollowed user (FollowStatusValue)
            
            Post post = iterator.next();

            if (post.getUser().isBanned()) { // condition to remove posts that are from banned users
                iterator.remove();
                continue;
            }

            if(post.getUser().equals(user)){ // condition to remove posts that are from the user themself
                iterator.remove();
                continue; 
            }

            for (ReadPost rp : readpost) { // Loop to remove posts that have been read
                if (post.equals(rp.getPost())) {
                    iterator.remove();
                    RPValue = true;
                    break;
                }
            }

            if (RPValue) {
                continue;
            }

            for (User bu : blockedUsers) { // Loop to remove posts that are from blocked user
                if (post.getUser().getUsername().equals(bu.getUsername())) {
                    iterator.remove();
                    BUValue = true;
                    break;
                }
            }

            if (BUValue) {
                continue;
            }

            for (FollowingStatus fs : followStatus) { // Loop to remove posts that are from unfollowed user
                if (fs.getStatus() == -1) {
                    iterator.remove();
                    FSValue = true;
                    break;
                }
            }

            if (FSValue) {
                continue;
            }


            for (UserHashtag uh : userHashtags) { // Loop to calculate value of post based on hashtag
                

                if (post.getHashtag1() == null) {
                    break;
                }

                if (post.getHashtag1().equals(uh.getHashtag())) {
                    post.setValue(uh.getValue());
                    continue;
                }


                if (post.getHashtag2() == null) {
                    continue;
                }

                if (post.getHashtag2() != null) {
                    if (post.getHashtag2().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }


                if (post.getHashtag3() == null) {
                    continue;
                }

                if (post.getHashtag3() != null) {
                    if (post.getHashtag3().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }


                if (post.getHashtag4() == null) {
                    continue;
                }

                if (post.getHashtag4() != null) {
                    if (post.getHashtag4().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }

                if (post.getHashtag5() == null) {
                    continue;
                }

                if (post.getHashtag5() != null) {
                    if (post.getHashtag5().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }

                if (post.getHashtag6() == null) {
                    continue;
                }

                if (post.getHashtag6() != null) {
                    if (post.getHashtag6().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }

                if (post.getHashtag7() == null) {
                    continue;
                }

                if (post.getHashtag7() != null) {
                    if (post.getHashtag7().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }

                if (post.getHashtag8() == null) {
                    continue;
                }

                if (post.getHashtag8() != null) {
                    if (post.getHashtag8().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }

                if (post.getHashtag9() == null) {
                    continue;
                }

                if (post.getHashtag9() != null) {
                    if (post.getHashtag9().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }

                if (post.getHashtag10() == null) {
                    continue;
                }

                if (post.getHashtag10() != null) {
                    if (post.getHashtag10().equals(uh.getHashtag())) {
                        post.setValue(uh.getValue());
                        continue;
                    }
                }
            }            
        }

        // Custom comparator for sorting posts
        Comparator<Post> postComparator = Comparator
                .comparingDouble(Post::getValue).reversed()
                .thenComparingInt(Post::getRepostCount).reversed()
                .thenComparingInt(Post::getReplyCount).reversed()
                .thenComparingInt(Post::getViewCount).reversed();

        // Sorting the list using the custom comparator
        posts.sort(postComparator);

        List<Post> returnList = posts.subList(0, 21); // Returns the first 20 posts

        return returnList;
    }
}