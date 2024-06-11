package com.example.Xcrack.Service.Implementation;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.ReadPost;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Model.UserHashtag;
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
    private UserHashtagRepository userHashtagRepository;

    @Autowired
    private ReadPostServiceImpl readPostServiceImpl;

    public List<Post> getPosts(String username) {
        List<Post> posts = postRepository.findAll();
        User user = userRepository.findByUsername(username);
        List<User> blockedUsers = userRepository.findBlockedUsersByUsername(user.getUsername());
        List<UserHashtag> userHashtags = userHashtagRepository.findByUser(user);
        List<ReadPost> readpost = readPostRepository.findByUser(user);

        Iterator<Post> iterator = posts.iterator();

        // First loop to remove all the posts from banned users, read post, blocked user then adds the value of each hashtag

        while (iterator.hasNext()) {
            boolean RPValue = false; // flag to continue to the next post if the post has been read (ReadPostValue)
            boolean BUValue = false; // flag to continue to the next post if the post is from a blocked user (BlockedUserValue)


            Post post = iterator.next();

            if (post.getUser().isBanned()) { // condition to remove posts that are from banned users
                iterator.remove();
                continue;
            }

            if (post.getUser().equals(user)) { // condition to remove posts that are from the user themself
                iterator.remove();
                continue;
            }

            if (readpost != null) {
                for (ReadPost rp : readpost) { // Loop to remove posts that have been read
                    if (post.equals(rp.getPost())) {
                        iterator.remove();
                        RPValue = true;
                        break;
                    }
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

            for (UserHashtag uh : userHashtags) { // Loop to calculate value of post based on hashtag

                for (Hashtag hashtag : post.getHashtags()) {
                    if (uh.getHashtag().equals(hashtag)) {
                        post.setValue(uh.getValue());
                    }
                }
            }
            postRepository.save(post);
        }

        // Custom comparator for sorting posts
        Comparator<Post> postComparator = Comparator
                .comparingInt(Post::getValue)
                .thenComparingInt(Post::getReplyCount)
                .thenComparingInt(Post::getLikeCount)
                .thenComparingInt(Post::getViewCount).reversed();

       
        posts.sort(postComparator);  // Sorting the list using the custom comparator

        List<Post> returnList = posts.subList(0, 5); // Returns the first 20 posts

        for (Post post : posts) {
            post.ResetValue();
            postRepository.save(post);
        }

        for (Post addNewReadPost : returnList) {
            readPostServiceImpl.AddReadPost(addNewReadPost, user);
            addNewReadPost.setViewCount(addNewReadPost.getViewCount() + 1);
            postRepository.save(addNewReadPost);
        }

        return returnList;
    }
}