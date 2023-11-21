package com.mycompany.mysqlimplements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAOImpl implements UsersDAO{
Model model;
    public UsersDAOImpl() {
        model = new Model();
        
    }
public void createTable()throws SQLException{
    Connection con = Connect.connection();
    PreparedStatement stmt =con.prepareStatement("create table if not exists users(email varchar(100) primary key not null,pass varchar(30));");
    stmt.execute();
    System.out.println("Table users has been create");
}
    @Override
    public void insertUsers(Model model) {
     Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("insert into users (email,pass) VALUES(?,?);");
                stmt.setString(1,model.getEmail());
                stmt.setString(2,model.getPassword());
             int rowsafecfed = stmt.executeUpdate();
             if (rowsafecfed > 0){
             System.out.println("User has been insert with success ");
         }else{
             System.out.println("error, user not inserted");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}
    @Override
    public void findUsers() {
 Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String email = rs.getString("email");
                String senha = rs.getString("pass");
                System.out.println("E-mail: " +email);
                System.out.println("Senha: "+senha);
                
            }
        } catch (SQLException ex) {
            System.err.println("error, to search "+ ex);    }
    }
    @Override
    public void status() {
         Connection con = Connect.connection();
        try {
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
