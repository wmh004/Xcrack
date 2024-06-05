package com.example.Xcrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Xcrack.Model.Message;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.MessageService;
import com.example.Xcrack.Service.UserService;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messageRequest) {
        User sender = userService.getUserById(messageRequest.getSenderId());
        User receiver = userService.getUserById(messageRequest.getReceiverId());
        Message message = messageService.sendMessage(sender, receiver, messageRequest.getContent(), messageRequest.getMediaList());
        return ResponseEntity.ok(message);
    }

    @PostMapping("/delete/{messageId}")
    public void deleteMessage(@PathVariable int messageId) {
        messageService.deleteMessage(messageId);
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<Message>> getSentMessages(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        List<Message> messages = messageService.getMessagesSentByUser(user);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<Message>> getReceivedMessages(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        List<Message> messages = messageService.getMessagesReceivedByUser(user);
        return ResponseEntity.ok(messages);
    }
}