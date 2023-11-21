package com.mycompany.mysqlimplements;
import java.sql.SQLException;
import java.util.Scanner;
public class UsersCrud {
    static UsersDAO  dao = new UsersDAOImpl();
 
    public static void chooseOperation() throws SQLException{
        Scanner s = new Scanner(System.in);
        String resp = "y";
        while (resp.equals("y")) {
        System.out.println("You choose what you wish to do: ");
        System.out.println("1. To insert a new user");
        System.out.println("2. Create table named users");
        System.out.println("3. To find all the users registered");
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
    }
    System.out.println("Wish to continue: ");
    resp = s.next();
        }
    }
    public static void insert(){
     Scanner s = new Scanner(System.in);
     Model model = new Model();
     System.out.println("Enter the e-mail: ");
     model.setEmail(s.nextLine());
     System.out.println("Enter the password: ");
     model.setPassword(s.nextLine()); 
     dao.insertUsers(model);
    }
    public static void findUsers(){
     dao.findUsers();
    }
    public static void create() throws SQLException{
        dao.createTable();
    }
}
