package com.example.Xcrack.Service;

import java.util.List;

import com.example.Xcrack.Model.Notification;
import com.example.Xcrack.Model.User;

public interface NotificationService {

    public Notification createNotification(User user, String content, int postID);
    public List<Notification> getAllNotificationsByUsername(String username);

}
