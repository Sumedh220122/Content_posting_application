package com.example.demo;

import java.util.List;

public interface PostFactory {
    boolean create_post(String username, Post post);

    List<String> userpostdetails(String username);

    List<String> getpostdetails(String username, String content);

    void likepost(Post post);

    void dislikepost(Post post);

    int getpostid(String username, String content);

    void deletepost(int id);

    List<List<String>> getalldetails(String username);

    void updatepost(String username, String oldcontent, String newcontent);

}
