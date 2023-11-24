package com.mycompany.mysqlimplements;

import java.sql.SQLException;
import java.util.Scanner;

public class UsersCrud {
    UsersDAO dao = new UsersDAOImpl();
    Model model;
    public UsersCrud() {
        this.model = new Model();
    }

    public void chooseOperation() throws SQLException {
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
                    insert();
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    findUsers();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    update();
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
    }

    public void insert() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the e-mail: ");
        model.setEmail(s.nextLine());
        String email = dao.searchUser(model);
        if(!model.getEmail().equals(email)){
        System.out.println("Enter the password: ");
        model.setPassword(s.nextLine());
        dao.insertUsers(model);
        }else{
            System.out.println("User already registered");
        }
    }

    public void findUsers() {
        dao.findUsers();
    }

    public void create() throws SQLException {
        dao.createTable();
    }

    public void delete() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the email of the user that will be deleted");
        model.setEmail(s.nextLine());
        String email = dao.searchUser(model);
        if (model.getEmail().equals(email)) {
            dao.deleteUsers(model);
        } else {
            System.out.println("User not found in the database");
        }
    }

    public void update() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the email of the user that will be updated");
        model.setEmail(s.nextLine());
        String email = dao.searchUser(model);
        if (model.getEmail().equals(email)) {
            System.out.println("Enter your new password: ");
            model.setPassword(s.nextLine());
            dao.updateUsers(model);
        } else {
            System.out.println("User not found in the database");
        }
    }
}
