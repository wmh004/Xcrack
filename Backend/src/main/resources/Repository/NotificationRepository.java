package com.example.Xcrack.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Xcrack.Model.Notification;
import com.example.Xcrack.Model.User;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> getAllNotificationsByUser(User user);

}