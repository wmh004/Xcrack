package com.example.Xcrack.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_hashtag")
public class UserHashtag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hashtag_id", referencedColumnName = "ID")
    private Hashtag hashtag;

    @Column(nullable = false)
    private int value;

    // Constructors
    public UserHashtag() {
    }

    public UserHashtag(User user, Hashtag hashtag, int value) {
        this.user = user;
        this.hashtag = hashtag;
        this.value = value;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    

    // toString() method
    @Override
    public String toString() {
        return "UserHashtag{" +
                "id=" + id +
                ", user=" + user +
                ", hashtag=" + hashtag +
                ", value=" + value +
                '}';
    }
}
