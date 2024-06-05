package com.example.Xcrack.Controller;

import java.util.List;

import com.example.Xcrack.Model.Media;

public class MessageRequest {
    private int senderId;
    private int receiverId;
    private String content;
    private List<Media> mediaList;
    private List<String> taggedUsername;

    public List<String> getTaggedUsername() {
        return taggedUsername;
    }

    public void setTaggedUsername(List<String> taggedUsername) {
        this.taggedUsername = taggedUsername;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
}