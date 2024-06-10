package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Xcrack.Model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query("SELECT r FROM Reply r WHERE r.id = :id AND r.deleted = false")
    public Reply getReplyById(int id);

}
