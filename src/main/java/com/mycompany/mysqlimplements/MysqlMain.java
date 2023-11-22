package com.mycompany.mysqlimplements;

import java.sql.SQLException;

public class MysqlMain {
    public static void main(String[] args) throws SQLException {
        UsersCrud usersCrud = new UsersCrud();
        usersCrud.chooseOperation();
    }
}
