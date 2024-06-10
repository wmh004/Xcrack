package com.example.Xcrack.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.user.username = :username")
    public List<Post> getPostListByUsername(@Param("username") String username);
    @Query("SELECT p FROM Post p WHERE p.id = :ID AND p.deleted = false")
    public Post getPostById(int ID);
    @NonNull
    @Query("SELECT p FROM Post p WHERE p.deleted = false")
    public List<Post> findAll();
    List<Post> findByContentContaining(String content);
    @Query("SELECT p FROM Post p JOIN p.hashtags h WHERE h IN :hashtags AND p.deleted = false")
    List<Post> findByHashtagsIn(@Param("hashtags") Set<Hashtag> hashtags);
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND " +
           "(:content IS NULL OR p.content LIKE %:content%) AND " +
           "(:hashtags IS NULL OR EXISTS (SELECT 1 FROM p.hashtags h WHERE h IN :hashtags))")
    List<Post> findByContentAndHashtagsIn(@Param("content") String content, @Param("hashtags") Set<Hashtag> hashtags);

}