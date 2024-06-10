package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Xcrack.Model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query("SELECT p FROM Post p WHERE p.id = :ID AND p.deleted = false")
    public Reply getReplyById(int ID);

}
