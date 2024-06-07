package com.example.Xcrack.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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

    @ElementCollection
    @CollectionTable(name = "user_tagged_usernames", 
                    joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "tagged_username")
    private List<String> taggedUsername;

    @ManyToOne
    @JoinColumn(name = "hashtag1_id")
    private Hashtag hashtag1;

    @ManyToOne
    @JoinColumn(name = "hashtag2_id")
    private Hashtag hashtag2;

    @ManyToOne
    @JoinColumn(name = "hashtag3_id")
    private Hashtag hashtag3;

    @ManyToOne
    @JoinColumn(name = "hashtag4_id")
    private Hashtag hashtag4;

    @ManyToOne
    @JoinColumn(name = "hashtag5_id")
    private Hashtag hashtag5;

    @ManyToOne
    @JoinColumn(name = "hashtag6_id")
    private Hashtag hashtag6;

    @ManyToOne
    @JoinColumn(name = "hashtag7_id")
    private Hashtag hashtag7;

    @ManyToOne
    @JoinColumn(name = "hashtag8_id")
    private Hashtag hashtag8;

    @ManyToOne
    @JoinColumn(name = "hashtag9_id")
    private Hashtag hashtag9;

    @ManyToOne
    @JoinColumn(name = "hashtag10_id")
    private Hashtag hashtag10;

    @ElementCollection
    @JoinTable(name = "post_hashtags", 
            joinColumns = @JoinColumn(name = "post_id"))
    private Set<Hashtag> hashtags = new HashSet<>();

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

    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public Hashtag getHashtag1() {
        return hashtag1;
    }

    public void setHashtag1(Hashtag hashtag1) {
        this.hashtag1 = hashtag1;
    }

    public Hashtag getHashtag2() {
        return hashtag2;
    }

    public void setHashtag2(Hashtag hashtag2) {
        this.hashtag2 = hashtag2;
    }

    public Hashtag getHashtag3() {
        return hashtag3;
    }

    public void setHashtag3(Hashtag hashtag3) {
        this.hashtag3 = hashtag3;
    }

    public Hashtag getHashtag4() {
        return hashtag4;
    }

    public void setHashtag4(Hashtag hashtag4) {
        this.hashtag4 = hashtag4;
    }

    public Hashtag getHashtag5() {
        return hashtag5;
    }

    public void setHashtag5(Hashtag hashtag5) {
        this.hashtag5 = hashtag5;
    }

    public Hashtag getHashtag6() {
        return hashtag6;
    }

    public void setHashtag6(Hashtag hashtag6) {
        this.hashtag6 = hashtag6;
    }

    public Hashtag getHashtag7() {
        return hashtag7;
    }

    public void setHashtag7(Hashtag hashtag7) {
        this.hashtag7 = hashtag7;
    }

    public Hashtag getHashtag8() {
        return hashtag8;
    }

    public void setHashtag8(Hashtag hashtag8) {
        this.hashtag8 = hashtag8;
    }

    public Hashtag getHashtag9() {
        return hashtag9;
    }

    public void setHashtag9(Hashtag hashtag9) {
        this.hashtag9 = hashtag9;
    }

    public Hashtag getHashtag10() {
        return hashtag10;
    }

    public void setHashtag10(Hashtag hashtag10) {
        this.hashtag10 = hashtag10;
    }
}