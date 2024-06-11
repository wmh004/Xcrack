package com.example.Xcrack.Service;

import java.util.List;

import com.example.Xcrack.Model.Reply;

public interface ReplyService {

    public Reply getReplyById(int ID);
    public Reply createReply(String content, String username, int parentPostId);
    public void removeReply(int replyId);
    public List<Reply> getRepliesByUsername(String username);
    public void likeReply(int replyId, int userId);
    
}
