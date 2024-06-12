package com.example.Xcrack.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.ReadPost;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.ReadPostRepository;
import com.example.Xcrack.Service.ReadPostService;

@Service
public class ReadPostServiceImpl implements ReadPostService{

    @Autowired
    private ReadPostRepository readPostRepository; 

    public void AddReadPost(Post post, User user){
        ReadPost newReadPost = new ReadPost(user, post); 
        readPostRepository.save(newReadPost); 
    }
}
