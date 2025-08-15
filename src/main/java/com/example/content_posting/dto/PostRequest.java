package com.example.content_posting.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PostRequest {
    public UUID community_id;
    public String title;
    public String content;
}
