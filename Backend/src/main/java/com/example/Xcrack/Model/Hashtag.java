package com.example.Xcrack.Model;

import java.util.ArrayList;
import java.util.HashSet;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hashtag")
public class Hashtag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false, unique = true)
    private String hashtag;

    @OneToMany(mappedBy = "hashtag")
    @JsonIgnore
    private List<UserHashtag> userHashtags = new ArrayList<>();

    @Column(nullable = false)
    private int count; 

    @ManyToMany(mappedBy = "hashtags")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    // Getter and Setter

    public int getID() {
        return ID;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public List<UserHashtag> getUserHashtags() {
        return userHashtags;
    }
    
    public void setUserHashtags(List<UserHashtag> userHashtags) {
        this.userHashtags = userHashtags;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<Post> getPosts(){
        return this.posts; 
    }

    public void setPosts(Set<Post> posts){
        this.posts = posts; 
    }
    public void incrementCount() {
        this.count++;
    }
}
