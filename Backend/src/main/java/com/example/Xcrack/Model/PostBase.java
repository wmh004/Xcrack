package com.example.Xcrack.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalTimeConverter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class PostBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateCreated;

    @Column(nullable = false)
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime timeCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    private int replyCount;
    private int repostCount;
    private int likeCount;
    private int bookmarkCount;
    private int shareCount;
    private int viewCount;
    private int value = 0;
    private boolean deleted = false;

    @ElementCollection
    @CollectionTable(name = "user_tagged_usernames", 
                    joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "tagged_username")
    private List<String> taggedUsername;

    public PostBase() {}

    public PostBase(String content, User user) {
        this.content = content;
        this.user = user;
        this.dateCreated = LocalDate.now();
        this.timeCreated = LocalTime.now();
        this.username = user.getUsername();
        this.name = user.getName();
    }

    // Getter and Setter methods

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value += value;
    }

    public void ResetValue(){
        this.value = 0; 
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(int repostCount) {
        this.repostCount = repostCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(int bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<String> getTaggedUsername() {
        return taggedUsername;
    }

    public void setTaggedUsername(List<String> taggedUsername) {
        this.taggedUsername = taggedUsername;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}