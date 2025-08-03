package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class NotificationFactory {
    public Notification createNotification(int recipientid, String from, String to, String message) {
        return Notification.getInstance2(recipientid, from, to, message);
    }
}

public class Notification {
    int recipientid;
    String message;
    String from;
    String to;

    private static Notification instance;

    private Notification(){

    }

    private Notification(int recipientid,String from, String to, String message){
        this.message = message;
        this.recipientid = recipientid;
        this.from = from;
        this.to = to;
    }

    public static Notification getInstance(){
        if(instance == null){
            instance = new Notification();
        }

        return instance;
    }

    public static Notification getInstance2(int recipientid,String from, String to, String message){
        if(instance == null){
            instance = new Notification(recipientid, from, to, message);
        }

        return instance;
    }

    public void send_message(Notification n){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "INSERT INTO notification(recipientid, `from`, `to`, message) VALUES(?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, n.recipientid);
            statement.setString(2, n.from);
            statement.setString(3, n.to);
            statement.setString(4,  n.message);

            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            return;
        }
    }

    public List<List<String>> getmessages(int id){
        List<List<String>> messages = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Select * from notification where recipientid = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                List<String> temp = new ArrayList<>();
                temp.add(result.getString("from"));
                temp.add(result.getString("message"));

                messages.add(temp);
            }

            return messages;

        }catch(SQLException e){
            e.printStackTrace();
            return messages;
        }
    }
}