package com.example.content_posting.repository;

import com.example.content_posting.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<Community, UUID>{
    Community findByName(String name);
}
