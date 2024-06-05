package com.example.Xcrack.Service;

import java.util.List;

import com.example.Xcrack.Model.Media;
import com.example.Xcrack.Model.Message;
import com.example.Xcrack.Model.User;

public interface MessageService {

    public Message sendMessage(User sender, User receiver, String content,List<Media> mediaList);
    public void deleteMessage(int messageId);
    public void tagUserInMessage(int messageId, String taggedUsername);
    public List<Message> getMessagesSentByUser(User sender);
    public List<Message> getMessagesReceivedByUser(User receiver);

}
