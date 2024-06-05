package com.example.Xcrack.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Model.Notification;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.NotificationRepository;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Notification createNotification(User user, String content, int postID) {
        Notification notification = new Notification(user, content, postID);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotificationsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return notificationRepository.getAllNotificationsByUser(user);
    }

}
