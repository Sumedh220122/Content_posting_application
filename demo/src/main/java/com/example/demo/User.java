package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {

    int id;
    String username;
    String emailid;
    String password;
    int num_followers;
    int num_following;
    List<String> Posts;

    public User(){

    }

    public User(int id, String username, String emailid, String password, int nf, int nfol){
        this.id = id;
        this.username = username;
        this.emailid = emailid;
        this.password = password;
        this.num_followers = nf;
        this.num_following = nfol;
    }

    public boolean login(String username, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "SELECT * FROM user WHERE Username = ? AND Password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } 
    
    public boolean Signup(User user){
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "INSERT into user(Username, EmailID, Password, num_followers, num_following) VALUES(?, ?, ?, ?, ? )";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.username);
            statement.setString(2, user.emailid);
            statement.setString(3, user.password);
            statement.setInt(4, 0);
            statement.setInt(5, 0);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }


        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getuserdetails(String username){
        ResultSet resultSet = null;
        List<String> details = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "select Username, num_followers, num_following from User where Username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
               details.add(resultSet.getString("Username"));
               details.add(Integer.toString(resultSet.getInt("num_followers")));
               details.add(Integer.toString(resultSet.getInt("num_following")));

               return details;
            }

            return details;

        }catch(SQLException e){
            e.printStackTrace();

            return details;
        }
    }

    public void follow(String user1, String user2){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "UPDATE User SET num_followers = num_followers + 1 where username = ?";
            
            String sql2 = "UPDATE User SET num_following = num_following + 1 where username = ?";

            User user = new User();

            int followerid = user.getuserid(user2);
            int followingid = user.getuserid(user1);

            String sql3 = "INSERT INTO followers(username, followerid, followername) values(?, ?, ?)";
            String sql4 = "INSERT INTO following(username, followingid, followingname) values(?, ?, ?)";


            PreparedStatement statement = connection.prepareStatement(sql);

            PreparedStatement statement2 = connection.prepareStatement(sql2);

            PreparedStatement statement3 = connection.prepareStatement(sql3);

            PreparedStatement statement4 = connection.prepareStatement(sql4);

            statement.setString(1, user1);
            statement2.setString(1, user2);

            statement3.setString(1, user1);
            statement3.setInt(2, followerid);
            statement3.setString(3, user2);

            statement4.setString(1, user2);
            statement4.setInt(2, followingid);
            statement4.setString(3, user1);

            statement.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
            statement4.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void unfollow(String user1, String user2){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "UPDATE User SET num_followers = num_followers - 1 where username = ? && num_followers > 0";

            String sql2 = "UPDATE User SET num_following = num_following - 1 where username = ? && num_following > 0";

            String sql3 = "delete from followers where followerid = ?";

            String sql4 = "delete from following where followingid = ?"; 

            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);

            User user = new User();

            int followerid = user.getuserid(user2);
            int followingid = user.getuserid(user1);


            statement.setString(1, user1);
            statement2.setString(1, user2);
            statement3.setInt(1, followerid);
            statement4.setInt(1, followingid);

            statement.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
            statement4.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getuserid(String user){
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Select userid from user where Username = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user);

            ResultSet result = statement.executeQuery();

            result.next();
            return result.getInt("userid");

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getfollowers(String username){
        List<String> followers = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Select followername from followers where username = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

           while(result.next()){
             followers.add(result.getString("followername"));
           }

           return followers;

        }catch(SQLException e){
            e.printStackTrace();
            return followers;
        }
    }

    public List<String> getfollowing(String username){
        List<String> following = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Content_Posting",
                "root", "Sumedhmysql@22");

            String sql = "Select followingname from following where username = ?";


            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

           while(result.next()){
             following.add(result.getString("followingname"));
           }

           return following;

        }catch(SQLException e){
            e.printStackTrace();
            return following;
        }
    }

    public int find_foll(String user1, String user2){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "select status from followed where user1 = ? && user2 = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user1);
            statement.setString(2, user2);


            ResultSet result = statement.executeQuery();

            if(result.next()){
                return result.getInt("status");
            }
            else{
                return 0;
            }            

        }catch(SQLException e){
            e.printStackTrace();

            return -1;
        }
    }

    public void insertintofoll(String user1, String user2, int val){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "insert into followed(user1, user2, status) values(?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user1);
            statement.setString(2, user2);
            statement.setInt(3, val);

            statement.executeUpdate();        

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateunfoll(String user1, String user2, int val){
        try{
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Content_Posting",
                    "root", "Sumedhmysql@22");

            String sql = "update followed set status = ? where user1 = ? && user2 = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, val);
            statement.setString(2, user1);
            statement.setString(3, user2);

            statement.executeUpdate();        

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
