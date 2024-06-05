package com.example.Xcrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Service.PostService;
import com.example.Xcrack.Service.ReplyService;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @PostMapping("/{postId}")
    public Post getPost(@PathVariable int postId) {
        return postService.getPostByPostID(postId);
    }
    
    
    @PostMapping("/{username}/create-post")
    public Post createPost(@RequestBody Post post, @PathVariable String username) {
        return postService.createPost(post.getContent(), username, post.getMediaList());
    }

    @PostMapping("{postId}/remove-post")
    public void removePost(@PathVariable int postId) {
        postService.removePost(postId);
    }

    @PostMapping("{username}/create-reply")
    public Reply createReply(@RequestBody Reply reply, @PathVariable String username) {
        return replyService.createReply(reply.getContent(), username, reply.getParentPost().getId(), reply.getMediaList());
    }

    @PostMapping("{postId}/remove-reply")
    public void removeReply(@PathVariable int replyId) {
        replyService.removeReply(replyId);
    }

    @GetMapping("/{username}")
    public List<Post> getPostsByUsername(@PathVariable String username) {
        return postService.getPostsByUsername(username);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/{postId}/share")
    public ResponseEntity<Void> sharePost(@PathVariable int postId) throws Exception {
        postService.sharePost(postId);
        return ResponseEntity.ok().build();
    }
}