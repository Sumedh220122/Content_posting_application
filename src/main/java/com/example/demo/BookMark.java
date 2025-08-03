package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMark {
    String user;
    int id;

    private static BookMark instance;

    private BookMark(){

    }

    private BookMark(String user, int id){
        this.user = user;
        this.id = id;
    }

    public static BookMark getInstance(){
        if(instance == null){
            instance = new BookMark();
        }

        return instance;
    }

    public static BookMark getInstance2(String user, int id){
        if(instance == null){
            instance = new BookMark(user, id);
        }

        return instance;
    }

    

    public void createBookMark(BookMark b){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Insert into bookmark(username, postid) values(?, ?)";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, b.user);
            statement.setInt(2, b.id);


            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Integer> getpostid(String username){
        List<Integer> ids = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "select postid from bookmark where username = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);


            ResultSet result = statement.executeQuery();

            while(result.next()){
                ids.add(result.getInt("postid"));
            }

            return ids;

        }catch(SQLException e){
            e.printStackTrace();
            return ids;
        }
    }

    public List<String> getbookmarks(int id){
        List<String> details = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Select username, content from post where postid = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                details.add(result.getString("username"));
                details.add(result.getString("content"));
            }

            return details;

        }catch(SQLException e){
            e.printStackTrace();

            return details;
        }
    }

    
}
