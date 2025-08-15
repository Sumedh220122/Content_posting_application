package com.example.content_posting.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateCommunityRequest {
    public String name;
    public String tags;
}
