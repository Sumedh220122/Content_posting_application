package com.example.content_posting.repository;

import com.example.content_posting.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    User findByEmailId(String emailId);
    boolean existsByEmailIdAndPassword(String emailId, String password);
    Optional<User> findByEmailIdAndPassword(String emailId, String password);
    List<User> findAllByIdNot(UUID id);
    List<User> findByUsernameContainingIgnoreCaseAndIdNot(String username, UUID id);
}

