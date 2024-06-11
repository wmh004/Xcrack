package com.example.Xcrack.Model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Reply extends PostBase {

    @ManyToOne
    @JoinColumn(name = "parent_post_id", nullable = false)
    @JsonBackReference(value = "post-replies")
    private Post parentPost;

    @ManyToMany
    @JoinTable(
        name = "reply_hashtag",
        joinColumns = @JoinColumn(name = "reply_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private Set<Hashtag> hashtags = new HashSet<>();

    public Reply() {}

    public Reply(String content, User user, Post parentPost) {
        super(content, user);
        this.parentPost = parentPost;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public Set<Hashtag> getHashtags() {
        return this.hashtags;
    }

    public void setHashtags(Hashtag hashtag) {
        this.hashtags.add(hashtag);
    }

    
}