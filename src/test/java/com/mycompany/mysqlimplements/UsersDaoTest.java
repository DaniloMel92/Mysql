package com.mycompany.mysqlimplements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import com.mycompany.mysqlimplements.Connection.Connect;
import com.mycompany.mysqlimplements.DAO.UsersDAO;
import com.mycompany.mysqlimplements.DAO.UsersDAOImpl;
import com.mycompany.mysqlimplements.Model.Model;

public class UsersDaoTest {
    Model model;
    UsersDAO usersDAO;

    public UsersDaoTest() {
        this.model = new Model();
        this.usersDAO = new UsersDAOImpl();
    }

    @Test
    public void testInsertUsersSuccessfully() {
        model.setEmail("danilokelve999@gmail.com");
        model.setPassword("danilo92");
        usersDAO.insertUsers(model);
        String email = search(model);
        assertNotNull(email);
    }

    @Test
    public void testDeleteUsersSucessfully() {
        model.setEmail("danilokelvemeireles45@gmail.com");
        usersDAO.deleteUsers(model);
        String email = search(model);
        assertNull(email);
    }

    @Test
    public void testOfSearchInTheDatabaseSuccessfully() {
        Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("select email from users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            }
            assertNotNull(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void TestMethodUpdateUserSuccessfully() {
        model.setEmail("danilokelvemeireles45@yahoo.com.br");
        String email = search(model);
        model.setPassword("54321");
        assertNotNull(email);
        usersDAO.updateUsers(model);
        String password = searchPassword(model);
        assertEquals("54321", password);
    }

    public String search(Model model) {
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

        }
        return null;
    }

    public String searchPassword(Model model) {
        Connection con = Connect.connection();
        try {
            PreparedStatement stmt = con.prepareStatement("select pass from users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String pass = rs.getString("pass");
                if (model.getPassword().equals(pass)) {
                    return pass;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }
}
