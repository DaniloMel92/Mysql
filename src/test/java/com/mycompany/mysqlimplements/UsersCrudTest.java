package com.mycompany.mysqlimplements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.mycompany.mysqlimplements.CRUD.UsersCrud;
import com.mycompany.mysqlimplements.Connection.Connect;
import com.mycompany.mysqlimplements.Model.Model;

public class UsersCrudTest {

    @Test
    public void TestCrudMethodInsertNewUserSuccessfully() throws SQLException {
        UsersCrud usersCrud = new UsersCrud();
        Model model = new Model();
        model.setEmail("danilokl@gmail.com");
        model.setPassword("danilo92");
        usersCrud.insert(model);
        String email = search(model);
        assertEquals("danilokl@gmail.com", email);
    }

    @Test
    public void TestCrudMethodInsertUserThatAlreadyExistInTheDatabaseMustToReturnInRoolback() {
        UsersCrud usersCrud = new UsersCrud();
        Model model = new Model();
        model.setEmail("danilokelvemeireles145@yahoo.com.br");
        model.setPassword("danilo");
        usersCrud.insert(model);
        String email = search(model);
        assertNotNull(email);

    }

    @Test
    public void TestCrudMethodDeleteUserSuccessFully() {
        UsersCrud usersCrud = new UsersCrud();
        Model model = new Model();
        model.setEmail("danilokelvemeireles45@yahoo.com.br");
        String email = search(model);
        assertNotNull(email);
        usersCrud.delete(model);
        String emailReturn = search(model);
        assertNull(emailReturn);
    }

    @Test
    public void TestCrudMethodDeleteUserThatNotExistInTheDatabaseMustReturnInRoolback() {
        UsersCrud usersCrud = new UsersCrud();
        Model model = new Model();
        model.setEmail("danilokelvemeireles45@yahoo.com.br");
        String email = search(model);
        usersCrud.delete(model);
        assertNull(email);

    }

    @Test
    public void TestCrudMethodUpdateUserSuccessfully() {
        UsersCrud usersCrud = new UsersCrud();
        Model model = new Model();
        model.setEmail("danilo92@gmail.com");
        String email = search(model);
        assertNotNull(email);
        model.setPassword("da");
        usersCrud.update(model);
        String password = searchPassword(model);
        assertEquals("da", password);

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
