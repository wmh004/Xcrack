package com.example.Xcrack.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Xcrack.Model.User;
import com.example.Xcrack.Model.UserHashtag;
import java.util.List;

public interface UserHashtagRepository extends JpaRepository<UserHashtag, Integer> {

    List<UserHashtag> findByUser(User user);
    
    // Add custom query methods if needed
    @Query("SELECT uh FROM UserHashtag uh " + "WHERE LOWER(uh.user.username) = LOWER(:username) " + "AND LOWER(uh.hashtag.hashtag) = LOWER(:hashtag)")
    Optional<UserHashtag> findByUserUsernameAndHashtagHashtagIgnoreCase(@Param("username") String username, @Param("hashtag") String hashtag);
    
    // @Query(value = "SELECT * FROM user_hashtag WHERE user_id = :userId ORDER BY value DESC LIMIT 3", nativeQuery = true)
    // List<UserHashtag> findTop3ByUserOrderByValueDesc(@Param("userId") User user);
}
