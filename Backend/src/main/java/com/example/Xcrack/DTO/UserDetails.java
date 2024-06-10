package com.example.Xcrack.DTO;
import java.time.LocalDate;
import java.util.List;

import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;

public class UserDetails {

    private String username;
    private String name;
    private String bio;
    private String profilePictureUrl;
    private String backgroundPictureUrl;
    private String location;
    private String websiteUrl;
    private LocalDate dob;
    private List<Integer> followersId;
    private List<Integer> followingId;
    private List<Integer> postsId;
    private List<Integer> repliesId;
    private List<User> followers;
    private List<User> following;
    private List<Post> posts;
    private List<Reply> replies;

    public UserDetails(String username, String name, String bio, String profilePictureUrl, String backgroundPictureUrl,
            String location, String websiteUrl, LocalDate dob, List<User> followers, List<User> following,
            List<Post> posts, List<Reply> replies) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.backgroundPictureUrl = backgroundPictureUrl;
        this.location = location;
        this.websiteUrl = websiteUrl;
        this.dob = dob;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.replies = replies;
    }

    public UserDetails(String username, String name, String bio, String profilePictureUrl, String backgroundPictureUrl,
    String location, String websiteUrl, LocalDate dob, List<User> followers, List<User> following) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.backgroundPictureUrl = backgroundPictureUrl;
        this.location = location;
        this.websiteUrl = websiteUrl;
        this.dob = dob;
        this.followers = followers;
        this.following = following;
        }
        
    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getBackgroundPictureUrl() {
        return backgroundPictureUrl;
    }

    public void setBackgroundPictureUrl(String backgroundPictureUrl) {
        this.backgroundPictureUrl = backgroundPictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowersId() {
        return followersId;
    }

    public void setFollowersId(List<Integer> followersId) {
        this.followersId = followersId;
    }

    public List<Integer> getFollowingId() {
        return followingId;
    }

    public void setFollowingId(List<Integer> followingId) {
        this.followingId = followingId;
    }

    public List<Integer> getPostsId() {
        return postsId;
    }

    public void setPostsId(List<Integer> postsId) {
        this.postsId = postsId;
    }

    public List<Integer> getRepliesId() {
        return repliesId;
    }

    public void setRepliesId(List<Integer> repliesId) {
        this.repliesId = repliesId;
    }

    
}

