package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public int getUserId(String username) {
        User user = new User();
        int id = user.getuserid(username);
        return id; 
    }
}
