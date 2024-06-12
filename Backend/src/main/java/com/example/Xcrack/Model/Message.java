package com.example.Xcrack.Model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags;

    @ElementCollection
    @CollectionTable(name = "user_tagged_usernames", joinColumns = @JoinColumn(name = "used_id"))
    @Column(name = "tagged_username")
    private List<String> taggedUsername;

    public Message() {}
    
    public Message(User user, User userMentioned, String string, Object object, Object object2) {}

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
    
    //Getter and Setter

    public User getSender() {
        return sender;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getTaggedUsername() {
        return taggedUsername;
    }

    public void setTaggedUsername(List<String> taggedUsername) {
        this.taggedUsername = taggedUsername;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return ID;
    }
    
} 