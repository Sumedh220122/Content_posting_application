package com.example.content_posting.repository;

import com.example.content_posting.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Post findByUserId(UUID userId);

    @Query(value = "select p from Post p where p.userId = :userId")
    List<Post> findPostsByUserId(@Param("userId") UUID userId);
}
