package com.example.Xcrack.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Reply extends PostBase {

    @ManyToOne
    @JoinColumn(name = "parent_post_id", nullable = false)
    @JsonBackReference(value = "post-replies")
    private Post parentPost;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "reply_hashtag",
        joinColumns = @JoinColumn(name = "reply_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
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

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }
}