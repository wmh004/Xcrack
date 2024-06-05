package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Xcrack.Model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    public Reply getReplyById(int ID);

}
