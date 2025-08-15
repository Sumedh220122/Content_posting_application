package com.example.content_posting.dto;

import lombok.Data;

@Data
public class SignupRequest {
    public String username;
    public String email_id;
    public String password;
}
