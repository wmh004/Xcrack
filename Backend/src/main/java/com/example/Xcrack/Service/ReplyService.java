package com.example.Xcrack.Service;

import java.util.List;

import com.example.Xcrack.Model.Media;
import com.example.Xcrack.Model.Reply;

public interface ReplyService {

    public Reply createReply(String content, String username, int parentPostId, List<Media> mediaList);
    public void removeReply(int replyId);
    public List<Reply> getRepliesByUsername(String username);
    public void likeReply(int replyId, int userId);
    
}
