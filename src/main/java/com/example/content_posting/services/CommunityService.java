package com.example.content_posting.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.content_posting.models.Community;
import com.example.content_posting.repository.CommunityRepository;
import com.example.content_posting.dto.CreateCommunityRequest;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository){
        this.communityRepository = communityRepository;
    }

    public UUID createCommunity(CreateCommunityRequest request){
        try{
            UUID id = UUID.randomUUID();
            String name = request.name;
            String tags = request.tags;

            Community community = new Community(id, name, tags);

            communityRepository.save(community);

            return id;
        }catch (Exception e) {
            throw new RuntimeException("Failed to create community", e);
        }
    }

    public Optional<Community> getCommunityById(UUID id){
        try{
            return communityRepository.findById(id);
        }catch (Exception e) {
            throw new RuntimeException("Failed to create community", e);
        }
    }

    public List<Community> getAllCommunities(){
        try{
            return communityRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException("Failed to create community", e);
        }
    }
}
