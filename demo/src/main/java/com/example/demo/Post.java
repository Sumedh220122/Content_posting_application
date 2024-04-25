package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Post implements PostFactory{
    int num_likes;
    int num_dislikes;
    String username;
    String content;

    List<String> Comments;

    Post(){
            
    }

    Post(String username, String content){
        this.username = username;
        this.content = content;
    }
    
    Post(int num_likes, int num_dislikes, String username, String content){
        this.num_dislikes = num_dislikes;
        this.content = content;
        this.num_likes = num_likes;
        this.username = username;
    }

    @Override
    public boolean create_post(String username, Post post){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "INSERT into Post(Username, Content, num_likes, num_dislikes) values(?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, post.content);
            statement.setInt(3, 0);
            statement.setInt(4, 0);

            int rowsInserted = statement.executeUpdate();
            
            if(rowsInserted > 0){
                return true;
            }

            return false;

        }catch(SQLException e){
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<String> userpostdetails(String username){
        List<String> posts = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "Select Content, num_likes, num_dislikes from post where Username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                posts.add(resultSet.getString("Content"));
            }

            return posts;

        }catch(SQLException e){
            e.printStackTrace();

            return posts;
        }
    }

    @Override
    public List<String> getpostdetails(String username, String content){
        ResultSet resultSet = null;
        List<String> details = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "select * from Post where Username = ? && Content = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, content);

            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
               details.add(resultSet.getString("Username"));
               details.add(resultSet.getString("content"));
               details.add(Integer.toString(resultSet.getInt("num_likes")));
               details.add(Integer.toString(resultSet.getInt("num_dislikes")));

            }

            return details;

        }catch(SQLException e){
            e.printStackTrace();

            return details;
        }
    }

    @Override
    public void likepost(Post post){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql1 = "UPDATE Post SET num_likes = num_likes + 1 where username = ? and content = ?";
            String sql2 = "UPDATE Post SET num_dislikes = num_dislikes - 1 where username = ? and content = ? && num_dislikes > 0";


            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);

            statement1.setString(1, post.username);
            statement1.setString(2, post.content);

            statement2.setString(1, post.username);
            statement2.setString(2, post.content);


            statement1.executeUpdate();
            statement2.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void dislikepost(Post post){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql1 = "UPDATE Post SET num_dislikes = num_dislikes + 1 where username = ? and content = ?";
            String sql2 = "UPDATE Post SET num_likes = num_likes - 1 where username = ? and content = ? && num_likes > 0";



            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);

            statement1.setString(1, post.username);
            statement1.setString(2, post.content);

            statement2.setString(1, post.username);
            statement2.setString(2, post.content);


            statement1.executeUpdate();
            statement2.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getpostid(String username, String content){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "SELECT Postid from Post where Username = ? && Content = ?";



            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, content);


            ResultSet result = statement.executeQuery();


            result.next();
            int id = result.getInt("Postid");

            return id;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void deletepost(int id){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Delete from post where postid = ?";



            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<List<String>> getalldetails(String username){
        List<List<String>> alldetails = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "select * from Post where Username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
               List<String> details = new ArrayList<>();
               details.add(resultSet.getString("Username"));
               details.add(resultSet.getString("content"));
               details.add(Integer.toString(resultSet.getInt("num_likes")));
               details.add(Integer.toString(resultSet.getInt("num_dislikes")));
               alldetails.add(details);

            }

            return alldetails;

        }catch(SQLException e){
            e.printStackTrace();

            return alldetails;
        }
    }

    @Override
    public void updatepost(String username, String oldcontent, String newcontent){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "Update post set content = ? where username = ? && content = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, newcontent);
            statement.setString(2, username);
            statement.setString(3, oldcontent);


            statement.executeUpdate();
            

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertimage(String username, String url, int id){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "insert into imageurls(postid, username, imageurl) values(?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, username);
            statement.setString(3, url);


            statement.executeUpdate();
            

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int find_liked(String username, String content){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "select liked_status from is_liked where username = ? && content = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, content);


            ResultSet result = statement.executeQuery();

            if(result.next()){
                return 1;
            }
            else{
                return 0;
            }            

        }catch(SQLException e){
            e.printStackTrace();

            return -1;
        }
    }

    public void insertintolike(String username, String content, int val){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "insert into is_liked(username, content, liked_status) values(?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, content);
            statement.setInt(3, val);

            statement.executeUpdate();        

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updatelike(String username, String content, int val){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "update is_liked set liked_status = ? where username = ? && content = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, val);
            statement.setString(2, username);
            statement.setString(3, content);

            statement.executeUpdate();        

        }catch(SQLException e){
            e.printStackTrace();
        }
    }



     
}
