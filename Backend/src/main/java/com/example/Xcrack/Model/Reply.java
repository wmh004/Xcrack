package com.example.Xcrack.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Reply extends PostBase {

    @ManyToOne
    @JoinColumn(name = "parent_post_id", nullable = false)
    private Post parentPost;

    @ElementCollection
    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList = new ArrayList<>();

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