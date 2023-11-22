package com.mycompany.mysqlimplements;

import java.util.Scanner;

public class Model {
    private String email;
    private String password;
    Scanner s = new Scanner(System.in);

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
