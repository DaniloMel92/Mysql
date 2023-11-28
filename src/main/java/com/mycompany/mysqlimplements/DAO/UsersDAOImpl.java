package com.mycompany.mysqlimplements.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.mysqlimplements.Connection.Connect;
import com.mycompany.mysqlimplements.Model.Model;

public class UsersDAOImpl implements UsersDAO {
    Model model;

    public UsersDAOImpl() {
        model = new Model();

    }

    public void createTable() throws SQLException {
        Connection con = Connect.connection();
        PreparedStatement stmt = con.prepareStatement(
                "create table if not exists users(email varchar(100) primary key not null,pass varchar(30));");
        stmt.execute();
        System.out.println("Table users has been create");
    }

    @Override
    public void insertUsers(Model model) {
        Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("insert into users (email,pass) VALUES(?,?)");
            stmt.setString(1, model.getEmail());
            stmt.setString(2, model.getPassword());
            int rowsafecfed = stmt.executeUpdate();
            if (rowsafecfed > 0) {
                System.out.println("User has been insert with success ");
            } else {
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
            PreparedStatement stmt = con.prepareStatement("select email from users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                System.err.println("---------------------------------------------");
                System.out.println("E-mail: " + email);
                System.err.println("---------------------------------------------");
                System.err.println();
            }
        } catch (SQLException ex) {
            System.err.println("error, to search " + ex);
        }
    }

    @Override
    public void deleteUsers(Model model) {
        Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("delete from users where email=?");
            stmt.setString(1, model.getEmail());
            stmt.execute();
            System.out.println("The user has been deleted");
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void status() {
        Connection con = Connect.connection();
        try {
            if (con == null) {
                System.out.println("error in the connection");
            } else {
                System.out.println("Connection successfully");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateUsers(Model model) {
        Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("update users set pass=? where email=?");
            stmt.setString(1, model.getPassword());
            stmt.setString(2, model.getEmail());
            stmt.executeUpdate();
            System.out.println("The user has been updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String searchUser(Model model) {
        Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("select email from users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                if (model.getEmail().equals(email)) {
                    return email;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
