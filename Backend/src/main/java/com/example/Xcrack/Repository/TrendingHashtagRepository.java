package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Xcrack.Model.Hashtag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrendingHashtagRepository extends JpaRepository<Hashtag, Integer> {

    Hashtag findByHashtag(String tag);

    @Query("SELECT h FROM Hashtag h ORDER BY h.count DESC")
    List<Hashtag> findTopTrendingHashtags(@Param("limit") int limit);
}
