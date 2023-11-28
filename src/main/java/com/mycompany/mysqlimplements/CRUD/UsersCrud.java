package com.mycompany.mysqlimplements.CRUD;

import java.sql.SQLException;
import java.util.Scanner;

import com.mycompany.mysqlimplements.DAO.UsersDAO;
import com.mycompany.mysqlimplements.DAO.UsersDAOImpl;
import com.mycompany.mysqlimplements.Model.Model;

public class UsersCrud {
    UsersDAO dao = new UsersDAOImpl();

    public void chooseOperation(Model model) throws SQLException {
        Scanner s = new Scanner(System.in);
        String resp = "y";
        while (resp.equals("y")) {
            System.out.println("You choose what you wish to do: ");
            System.out.println("1. To insert a new user");
            System.out.println("2. Create table named users");
            System.out.println("3. To find all the users registered");
            System.out.println("4. to Delete user");
            System.out.println("5. Update information of users");
            int choose = s.nextInt();
            switch (choose) {
                case 1:
                    insert(model);
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    findUsers();
                    break;
                case 4:
                    delete(model);
                    break;
                case 5:
                    update(model);
                    break;
            }
            System.out.println("Wish to continue [y/n]: ");
            resp = s.next();
            while (!resp.equals("y") && !resp.equals("n")) {
                System.out.println("you must to enter y to continue or n to exit");
                resp = s.next();
                if (resp.equals("n")) {
                    break;
                }

            }

        }
        s.close();
    }

    public void insert(Model model) {
        String email = dao.searchUser(model);
        if (email == null) {
            dao.insertUsers(model);
        } else {
            System.err.println("User already inserted");
        }
    }

    public void findUsers() {
        dao.findUsers();
    }

    public void create() throws SQLException {
        dao.createTable();
    }

    public void delete(Model model) {
        String email = dao.searchUser(model);
        if (model.getEmail().equals(email)) {
            dao.deleteUsers(model);
        } else {
            System.out.println("User not found in the database");
        }
    }

    public void update(Model model) {
        String email = dao.searchUser(model);
        if (model.getEmail().equals(email)) {
            dao.updateUsers(model);
        } else {
            System.out.println("User not found in the database");
        }
    }
}
