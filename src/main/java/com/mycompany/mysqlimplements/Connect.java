package com.mycompany.mysqlimplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Connect {
private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
private static final String URL = "jdbc:mysql://localhost:3306/usuarios";
private static final String USER = "root";
private static final String PASS = "";
public static Connection getConnection(){
    try {
        Class.forName(DRIVER);
           
        return DriverManager.getConnection(URL,USER,PASS);
        
        
    } catch (ClassNotFoundException | SQLException ex) {
        
throw new RuntimeException(null,ex);    }
  
}
public static void closeConnection(Connection con){
    if (con != null){
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("closing connection... ");
        }
    }
        
}
public static void closeConnection(Connection con, PreparedStatement stmt){
    if (stmt != null){
        try {
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("erro: "+ex);
        }
    }
    closeConnection(con);
        
}
}