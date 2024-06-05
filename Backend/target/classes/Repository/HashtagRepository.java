package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Xcrack.Model.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

    public Hashtag findByHashtag(String tag);

}
