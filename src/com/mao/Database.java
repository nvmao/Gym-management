package com.mao;

import java.sql.*;

public class Database {

    public static Database instance;

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    private Connection con;

    public static void main(String args[]){
        new Database();
    }

    private Database(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/Jzvp3MDZ5S?useSSL=false&autoReconnect=true","Jzvp3MDZ5S","gtr4qNL8rT");
            System.out.println("Connect to database server successful");
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public User login(String username,String pass){
        String query = "select * from Users where username = ? and password= ? ";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String email = rs.getString("email");
                String user = rs.getString("username");
                int confirm = rs.getInt("confirm");
                return new User(email,user,confirm);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean existedUser(String username){
        String query = String.format("select username from Users where username = '%s'",username);
        try{
            ResultSet rs = con.prepareStatement(query).executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean existedEmail(String email){
        String query = String.format("select email from Users where email = '%s'",email);
        try{
            ResultSet rs = con.prepareStatement(query).executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

   public int addUser(String email,String username,String pass){
        String query = "insert into Users(email,username,password) values(?,?,?)";
        int result = 0;
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,email);
            ps.setString(2,username);
            ps.setString(3,pass);
            result = ps.executeUpdate();
            System.out.println(result);
        }catch (SQLException e){
            System.out.println(e);
        }
        return result;
   }



}
