package com.example.Xcrack.Service.Implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Exception.NotFoundException;
import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.PostRepository;
import com.example.Xcrack.Repository.ReplyRepository;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.HashtagService;
import com.example.Xcrack.Service.NotificationService;
import com.example.Xcrack.Service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private UserHashtagServiceImpl userHashtagServiceImpl;

    @Override
    public Reply createReply(String content, String username, int parentPostId) {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new NotFoundException("User not found with username: " + username);

        Post parentPost = postRepository.getPostById(parentPostId);

        if (parentPost == null)
            throw new NotFoundException("Parent post not found with ID: " + parentPostId);

        Reply reply = new Reply(content, user, parentPost);

        Set<String> hashtags = extractHashtags(content);
        Set<String> mentions = extractMentions(content);

        processHashtags(user, hashtags, reply);
        processMentions(user, mentions, reply.getId());
        processHashtagsFromParentPost(user, parentPost);

        parentPost.addReply(reply);
        user.addReply(reply);
        return replyRepository.save(reply);
    }

    @Override
    public void removeReply(int replyId) {
        Reply reply = replyRepository.getReplyById(replyId);
    
        if (reply == null) {
            throw new NotFoundException("Reply not found with ID: " + replyId);
        }
    
        reply.setDeleted(true);
        reply.setContent("This reply has been deleted");
    
        replyRepository.save(reply);
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
                notificationService.createNotification(mentionedUser, (user.getUsername() + " mentioned you in a post"),
                        postID);
            }
        }
    }

    private void processHashtags(User user, Set<String> hashtags, Reply reply) {
        for (String hashtag : hashtags) {
            reply.setHashtags(hashtagService.addHashtag(hashtag));
            userHashtagServiceImpl.addUserHashtagFromPost(user, hashtag);
        }
    }

    private void processHashtagsFromParentPost(User user, Post post){
        for(Hashtag hashtag : post.getHashtags()){
            userHashtagServiceImpl.addUserHashtagFromReply(user, hashtag.getHashtag()); 
        }
    }

    @Override
    public List<Reply> getRepliesByUsername(String username) {
        return replyRepository.getReplyListByUsername(username);
    }

    @Override
    public void likeReply(int replyId, int userId) {
        // Retrieve the post from the database
        Reply reply = replyRepository.getReplyById(replyId);
        User user = userRepository.findById(userId);

        for(Hashtag hashtag : reply.getHashtags()){
            userHashtagServiceImpl.addUserHashtagFromLike(user, hashtag.getHashtag());
        }
        
        // Increment the like count
        reply.setLikeCount(reply.getLikeCount() + 1);

        // Save the updated post back to the database
        replyRepository.save(reply);
    }

    @Override
    public Reply getReplyById(int ID) {
        return replyRepository.getReplyById(ID);
    }
  
    @Override
    public List<Reply> getRepliesByParentId(int parentPostId) {
        return replyRepository.getRepliesByParentId(parentPostId);
    }
}