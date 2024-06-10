package com.example.Xcrack.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Xcrack.Model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query("SELECT p FROM Reply p WHERE p.deleted = false AND p.user.username = :username")
    public List<Reply> getReplyListByUsername(@Param("username") String username);
    @Query("SELECT p FROM Reply p WHERE p.id = :ID AND p.deleted = false")
    public Reply getReplyById(int ID);

}
