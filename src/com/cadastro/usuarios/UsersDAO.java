package com.cadastro.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO implements IUsersDAO{
Model model;
    public UsersDAO() {
        model = new Model();
    }
public void createTable()throws SQLException{
    Connection con = Connect.getConnection();
    PreparedStatement stmt =con.prepareStatement("create table if not exists users(email varchar(30),pass varchar(15))");
    stmt.execute();
    Connect.closeConnection(con, stmt);
}
    @Override
    public void insertUsers() {
    String email = "danilokelvemeireles45@gmail.com";
    String pass = "danilo92";
        try {
            Connection con = Connect.getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into users (email,pass) VALUES(?,?)");
            ResultSet rs = stmt.executeQuery("select email from users");
            while(rs.next()){
            if (email!=rs.getString("email")){
                stmt.setString(1,email);
                stmt.setString(2,pass);
             int rowsafecfed = stmt.executeUpdate();
             
             if (rowsafecfed > 0){
             System.out.println("User has been insert with success ");
             
         }else{
             System.out.println("error, user not inserted");
            }
            }else{
                System.out.println("User already registered");
            }
         }stmt.close();
         con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}

    @Override
    public void findUsers() {

        try {
            Connection con = Connect.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String email = rs.getString("email");
                String senha = rs.getString("pass");
                System.out.println("E-mail: " +email);
                System.out.println("Senha: "+senha);
                
            }stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("error, to search "+ ex);    }
    }
    @Override
    public void status() {
        try {
            Connection con = Connect.getConnection();
            if (con==null){
                System.out.println("error in connection");
            } else {
                System.out.println("Connection successfully");
            }
           
       } catch (Exception e) {
           System.err.println(e.getMessage());
       }
    }
}

