package com.example.Xcrack.Service;

import java.util.List;

import com.example.Xcrack.Model.Post;

public interface PostService {

    public Post createPost(String content, String username);
    public Post getPostByPostID(int postID);
    public void likePost(int postId, int userId);
    public void removePost(int postID);
    public void sharePost(int postID) throws Exception;
    public List<Post> getPostsByUsername(String username);
    public List<Post> getAllPosts();
    public List<Post> searchPosts(String content, List<String> hashtags);

}