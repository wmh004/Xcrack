package com.example.Xcrack.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Xcrack.Model.Message;
import com.example.Xcrack.Model.User;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findById(int ID);
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);

}
