package com.example.Xcrack.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post extends PostBase {

    @ElementCollection
    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "post-replies")
    private List<Reply> replies = new ArrayList<>();

    @ElementCollection
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "post-media")
    private List<Media> mediaList = new ArrayList<>();

    @ElementCollection
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    public Post() {}

    public Post(String content, User user) {
        super(content, user);
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
        reply.setParentPost(this);
    }

    public void removeReply(Reply reply) {
        this.replies.remove(reply);
        reply.setParentPost(null);
    }

    public void addMedia(Media media) {
        if (this.mediaList.size() >= 4) {
            throw new IllegalArgumentException("A post can have a maximum of 4 media items only.");
        }
        this.mediaList.add(media);
        media.setPost(this);
    }

    public void removeMedia(Media media) {
        this.mediaList.remove(media);
        media.setPost(null);
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

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}