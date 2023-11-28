package com.mycompany.mysqlimplements;

import java.sql.SQLException;

import com.mycompany.mysqlimplements.CRUD.UsersCrud;
import com.mycompany.mysqlimplements.Model.Model;

public class MysqlMain {
    public static void main(String[] args) throws SQLException {
        UsersCrud usersCrud = new UsersCrud();
        Model model = new Model();
        model.setEmail("danilokl@gmail.com");
        model.setPassword("danilo92");
        usersCrud.insert(model);
    }
}
