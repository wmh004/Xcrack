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

    public List<Post> getPostListByUsername(String username);
    public Post getPostById(int ID);
    @NonNull
    public List<Post> findAll();
    List<Post> findByContentContaining(String content);
    @Query("SELECT p FROM Post p JOIN p.hashtags h WHERE h IN :hashtags")
    List<Post> findByHashtagsIn(@Param("hashtags") Set<Hashtag> hashtags);
    @Query("SELECT p FROM Post p WHERE (:content IS NULL OR p.content LIKE %:content%) AND (:hashtags IS NULL OR EXISTS (SELECT 1 FROM p.hashtags h WHERE h IN :hashtags))")
    List<Post> findByContentAndHashtagsIn(@Param("content") String content, @Param("hashtags") Set<Hashtag> hashtags);

}