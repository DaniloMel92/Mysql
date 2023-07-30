package com.cadastro.usuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
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
            System.err.println("Fechando conexao... ");
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
public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet res){
    if (res != null){
        try {
            res.close();
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        }
    }
    closeConnection(con, stmt);
        
}
public static void status(){
    try {
         Connection con = getConnection();
         if (con==null){
             System.out.println("Ocorreu um erro na conexao");
         } else {
             System.out.println("Conexao bem sucedida");
         }
        
    } catch (Exception e) {
    }
   
    
    
}
public static void inserirUsuario(){
    Scanner s = new Scanner (System.in);
        System.out.println("Digite seu email: ");
        String email = s.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = s.nextLine();
        
        
  
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into cadastro (email,senha) VALUES(?,?)");
            ResultSet rs = stmt.executeQuery("select email from cadastro");
            while(rs.next()){
            if (email!=rs.getString("email")){
                stmt.setString(1,email);
                stmt.setString(2,senha);
             int rowsafecfed = stmt.executeUpdate();
             
             if (rowsafecfed > 0){
             System.out.println("Usuario inserido com sucesso");
             
         }else{
             System.out.println("erro usuario nao inserido");
            }
            }else{
                System.out.println("Usuario ja cadastrado");
            }
         }stmt.close();
         con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}
public static void buscarUsuarios(){
    
    try {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from cadastro");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            System.out.println("E-mail: " +email);
            System.out.println("Senha: "+senha);
            
        }stmt.close();
        con.close();
    } catch (SQLException ex) {
        System.err.println("Ocorreu um erro na busca "+ ex);    }
}
}

