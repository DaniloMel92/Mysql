package com.mycompany.mysqlimplements;

import java.sql.SQLException;

public interface UsersDAO {
    public void insertUsers(Model model);

    public void findUsers();

    public void status();

    public void createTable() throws SQLException;

    public void deleteUsers(Model model);

    public void updateUsers(Model model);

    public String searchUser(Model model);
}
