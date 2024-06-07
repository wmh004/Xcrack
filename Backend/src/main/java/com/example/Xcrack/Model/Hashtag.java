package com.example.Xcrack.Model;

import java.util.ArrayList;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hashtag")
public class Hashtag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false, unique = true)
    private String hashtag;

    @OneToMany(mappedBy = "hashtag")
    private List<UserHashtag> userHashtags = new ArrayList<>();

    @Column(nullable = false)
    private int count; 

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

    
}
