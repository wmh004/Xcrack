package com.example.Xcrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Xcrack.Model.ReadPost;
import com.example.Xcrack.Model.User;
import java.util.List;

public interface ReadPostRepository extends JpaRepository<ReadPost, Integer> {
    public List<ReadPost> findByUser(User user);
}
