package com.example.Xcrack.Service;

import java.util.List;

import com.example.Xcrack.Model.Media;
import com.example.Xcrack.Model.Post;

public interface PostService {

    public Post createPost(String content, String username, List<Media> mediaList);
    public void removePost(int postID);
    public void sharePost(int postID) throws Exception;
    public Post getPostByPostID(int postID);
    public List<Post> getPostsByUsername(String username);

    public List<Post> getAllPosts();
    public List<Post> searchPosts(String content, List<String> hashtags);

}