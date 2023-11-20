package com.mycompany.mysqlimplements;

import java.util.Scanner;

public class Model {
    private String email;
    private String password;
   Scanner s = new Scanner (System.in);

   public void insertEmailAndPassword(){
        System.out.println("Enter your email: ");
        this.email = s.nextLine();
        System.out.println("Enter your password: ");
        this.password = s.nextLine();
    }
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

}
