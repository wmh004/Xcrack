package com.example.Xcrack.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Exception.NotFoundException;
import com.example.Xcrack.Model.Message;
import com.example.Xcrack.Model.Tag;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.MessageRepository;
import com.example.Xcrack.Repository.TagRepository;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Message sendMessage(User sender, User receiver, String content) {
        Message message = new Message(sender, receiver, content);
        sender.addSentMessage(message);
        receiver.addReceivedMessage(message);
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(int messageId) {
        Message message = messageRepository.findById(messageId);
        message.getSender().removeSentMessage(message);
        message.getReceiver().removeReceivedMessage(message);
        messageRepository.delete(message);
    }

    @Override
    public List<Message> getMessagesSentByUser(User sender) {
        return messageRepository.findBySender(sender);
    }

    @Override
    public List<Message> getMessagesReceivedByUser(User receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    @Override
    public void tagUserInMessage(int messageId, String taggedUsername) {
        Message message = messageRepository.findById(messageId);
        User taggedUser = userRepository.findByUsername(taggedUsername);

        if (message == null)
            throw new NotFoundException("Message not found with ID: " + messageId);
        if (taggedUser == null)
            throw new NotFoundException("User not found with username: " + taggedUsername);

        Tag tag = new Tag(message, taggedUser);
        message.addTag(tag);
        tagRepository.save(tag);
    }

}