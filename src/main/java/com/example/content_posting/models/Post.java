package com.example.content_posting.models;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates default constructor
public class Post {

    @Id
    UUID id;

    @Column(name = "community_id")
    UUID communityId;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "num_likes")
    int num_likes;

    @Column(name = "num_dislikes")
    int num_dislikes;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public Post(UUID id, UUID community_id, UUID user_id, String title, String content,
                int num_likes, int num_dislikes){
        this.id = id;
        this.communityId = community_id;
        this.userId = user_id;
        this.title = title;
        this.content = content;
        this.num_likes = num_likes;
        this.num_dislikes = num_dislikes;
    }
}
