package com.example.content_posting.services;

import java.util.UUID;
import java.util.List;

import com.example.content_posting.dto.PostRequest;
import com.example.content_posting.models.Post;
import com.example.content_posting.models.User;
import com.example.content_posting.repository.PostRepository;
import com.example.content_posting.utils.SessionUtils;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(PostRequest request){
        try{
            User currentUser = SessionUtils.getCurrentUser();
            UUID user_id = currentUser.id;
            UUID community_id = request.community_id;
            UUID id = UUID.randomUUID();

            String post_content = request.content;
            String title = request.title;

            Post post = new Post(id, community_id, user_id, title, post_content, 0, 0);

            postRepository.save(post);

        }catch(Exception e){
            throw new RuntimeException("Failed to create post", e);
        }
    }

    public List<Post> getUserPosts(UUID id){
        try{
            return this.postRepository.findPostsByUserId(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
