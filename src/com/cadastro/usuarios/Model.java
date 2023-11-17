package com.cadastro.usuarios;

import java.util.Scanner;

public class Model {
    private String email;
    private String password;
   Scanner s = new Scanner (System.in);

   public String insertEmail(){
        System.out.println("Enter your email: ");
        this.email = s.nextLine();
    return this.email;     
    }
    public String insertPassword(){
        System.out.println("Enter your password");
        this.password = s.nextLine();
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
