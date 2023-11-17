package com.cadastro.usuarios;

import java.sql.SQLException;

public class ToConnectWithMysqlToRegisterNewsUsers {
    public static void main(String[] args) throws SQLException {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.status();
        usersDAO.insertUsers();
    }
}
