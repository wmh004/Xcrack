package com.example.Xcrack.Service.Implementation;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Exception.NotFoundException;
import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Media;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.HashtagRepository;
import com.example.Xcrack.Repository.PostRepository;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.HashtagService;
import com.example.Xcrack.Service.NotificationService;
import com.example.Xcrack.Service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserHashtagServiceImpl userHashtagServiceImpl; 

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public Post createPost(String content, String username, List<Media> mediaList) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new NotFoundException("User not found with username: " + username);
        }

        Post post = new Post(content, user);

        if (mediaList != null) {
            post.setMediaList(mediaList);
            mediaList.forEach(media -> media.setPost(post));
        } else  
            post.setMediaList(null);

        Set<String> hashtags = extractHashtags(content);
        Set<String> mentions = extractMentions(content);

        processHashtags(user, hashtags, post);
        processMentions(user, mentions, post.getId());

        user.addPost(post);
        
        return postRepository.save(post);
    }

    private Set<String> extractHashtags(String content) {
        content = content.toLowerCase();
        Set<String> hashtags = new HashSet<>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            hashtags.add(matcher.group().substring(1).toLowerCase()); // Remove the '#' character
        }
        return hashtags;
    }

    private Set<String> extractMentions(String content) {
        Set<String> mentions = new HashSet<>();
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            mentions.add(matcher.group().substring(1)); // Remove the '@' character
        }
        return mentions;
    }

    private void processMentions(User user, Set<String> mentions, int postID) {
        for (String username : mentions) {
            User mentionedUser = userRepository.findByUsername(username);
            if (mentionedUser != null) {
                notificationService.createNotification(mentionedUser, (user.getUsername() + " mentioned you in a post"), postID);
            }
        }
    }

    private void processHashtags(User user, Set<String> hashtags, Post post) {
        for (String hashtag : hashtags) {
            post.setHashtags(hashtagService.addHashtag(hashtag));
            userHashtagServiceImpl.addUserHashtagFromPost(user, hashtag);
        }
    }

    @Override
    public void removePost(int postId) {
        Post post = postRepository.getPostById(postId);

        if (post == null)
            throw new NotFoundException("Post not found with ID: " + postId);

        User user = post.getUser();
        user.removePost(post);
        postRepository.delete(post);
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        return postRepository.getPostListByUsername(username);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void sharePost(int postID) {
        Post post = postRepository.getPostById(postID);
        if (post != null) {
            post.setShareCount(post.getShareCount() + 1);
            postRepository.save(post);
        } else {
            throw new NotFoundException("Post not found with ID: " + postID);
        }
    }

    @Override
    public Post getPostByPostID(int postID) {
        return postRepository.getPostById(postID);
    }

    @Override
    public List<Post> searchPosts(String content, List<String> hashtagStrings) {
        Set<Hashtag> hashtags = new HashSet<>();
        if (hashtagStrings != null) {
            for (String tag : hashtagStrings) {
                Hashtag hashtag = hashtagRepository.findByHashtag(tag);
                if (hashtag != null) {
                    hashtags.add(hashtag);
                } else {
                    hashtagService.addHashtag(tag);
                    hashtag = hashtagRepository.findByHashtag(tag); // Fetching again after adding
                    if (hashtag != null) {
                        hashtags.add(hashtag);
                    }
                }
            }
        }
        if (content != null && !hashtags.isEmpty()) {
            return postRepository.findByContentAndHashtagsIn(content, hashtags);
        } else if (content != null) {
            return postRepository.findByContentContaining(content);
        } else if (!hashtags.isEmpty()) {
            return postRepository.findByHashtagsIn(hashtags);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void likePost(int postId, int userId) {
        // Retrieve the post from the database
        Post post = postRepository.getPostById(postId);

        // Increment the like count
        post.setLikeCount(post.getLikeCount() + 1);

        // Save the updated post back to the database
        postRepository.save(post);
    }
}