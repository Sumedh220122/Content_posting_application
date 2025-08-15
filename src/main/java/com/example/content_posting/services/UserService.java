package com.example.content_posting.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.content_posting.dto.LoginRequest;
import com.example.content_posting.dto.SignupRequest;
import com.example.content_posting.models.User;
import com.example.content_posting.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(SignupRequest data){
        try{
            UUID id = UUID.randomUUID();
            String username = data.username;
            String email_id = data.email_id;
            String password = data.password;

            User user = new User(id, username, email_id, password, 0, 0);

            userRepository.save(user);
        }catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }

    public Optional<User> handleLogin(LoginRequest data){
        try{
            String emailId = data.email_id;
            String password = data.password;

            return userRepository.findByEmailIdAndPassword(emailId, password);
        }catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }

    public Optional<User> getUserDetails(UUID id){
        try{
            return this.userRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsersExcept(UUID currentUserId){
        try{
            return this.userRepository.findAllByIdNot(currentUserId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch users", e);
        }
    }

    public List<User> searchUsersByQuery(String query, UUID currentUserId){
        try{
            return this.userRepository.findByUsernameContainingIgnoreCaseAndIdNot(query, currentUserId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search users", e);
        }
    }
}
