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

    Connection con;

    private Database(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gym?useSSL=false&autoReconnect=true","root","rtyfghvb");

        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public boolean login(String username,String pass){
        String query = "select * from Users where username = ? and password= ? ";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
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
        String query = "insert into Users values(?,?,?)";
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
