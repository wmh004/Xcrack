package com.example.Xcrack.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post extends PostBase {

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "post-replies")
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "post-tags")
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "post_hashtag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    @JsonIgnore
    private Set<Hashtag> hashtags = new HashSet<>();

    public Post() {
    }

    public Post(String content, User user) {
        super(content, user);
    }

    public Set<Hashtag> getHashtags() {
        return this.hashtags;
    }

    public void setHashtags(Hashtag hashtag) {
        this.hashtags.add(hashtag);
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
        reply.setParentPost(this);
    }

    public void removeReply(Reply reply) {
        this.replies.remove(reply);
        reply.setParentPost(null);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}