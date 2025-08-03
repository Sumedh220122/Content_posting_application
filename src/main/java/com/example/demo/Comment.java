package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Comment {

    public int commentid;
    public int postid;
    public String username;
    public String comment;
    public int likes;
    public int dislikes;

    private static Comment instance;

    private Comment(){

    }

    private Comment(int commentid, int postid, String username, String comment, int likes, int dislikes){
        this.commentid = commentid;
        this.postid = postid;
        this.username = username;
        this.comment = comment;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public static Comment getInstance(){
        if(instance == null){
            instance = new Comment();
        }

        return instance;
    }

    public static Comment getinstance2(int commentid, int postid, String username, String comment, int likes, int dislikes){
        if(instance == null){
            instance = new Comment(commentid,postid,username,comment,likes,dislikes);
        }

        return instance;
    }


    public boolean addComment(Comment co, String content, String user2){
        Post post = new Post();
        int postid = post.getpostid(user2, content);

        co.postid = postid;

        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

                String sql = "INSERT INTO comments(postid, username, comment, likes, dislikes) VALUES(?, ?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
    
                statement.setInt(1, co.postid);
                statement.setString(2, co.username);
                statement.setString(3, co.comment);
                statement.setInt(4,  co.likes);
                statement.setInt(5, co.dislikes);
    
                int rowsinserted = statement.executeUpdate();

                if(rowsinserted > 0){
                    return true;
                }

                return false;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    
    }

    public List<List<String>> getcomments(int id){
        List<List<String>> comments = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

                String sql = "Select username, comment, likes, dislikes from comments where postid = ?";

                PreparedStatement statement = connection.prepareStatement(sql);
    
                statement.setInt(1, id);
    
                ResultSet result = statement.executeQuery();

                while(result.next()){
                    List<String> temp = new ArrayList<>();
                    temp.add(result.getString("username"));
                    temp.add(result.getString("comment"));
                    temp.add(Integer.toString(result.getInt("likes")));
                    temp.add(Integer.toString(result.getInt("dislikes")));

                    comments.add(temp);
                }

                return comments;

        }catch(SQLException e){
            e.printStackTrace();
            return comments;
        }
    }

    public int getcommentid(String username, int postid, String comment){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "SELECT commentid from comments where Username = ? && postid = ? && comment = ?";



            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setInt(2, postid);
            statement.setString(3, comment);


            ResultSet result = statement.executeQuery();


            result.next();
            int id = result.getInt("commentid");

            return id;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public void like(int id){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "UPDATE comments set likes = likes + 1 where commentid = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);


            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void dislike(int id){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "UPDATE comments set dislikes = dislikes + 1 where commentid = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);


            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
