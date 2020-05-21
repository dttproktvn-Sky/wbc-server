package com.singalarity.wbc.server.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabaseQuery {
    String sqlFindUser = "SELECT * FROM mobileUser WHERE username = ";
    String connectionUrl = "jdbc:mysql://localhost:3306/db";
 
    public String checkPassword(String username){        
        try {
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }          
           // Setup the connection with the DB
            Connection conn = DriverManager.getConnection(connectionUrl,"user","password");
            System.out.println("connect successfully");
            String query = sqlFindUser+"'"+ username+"'";
            System.out.println(query);
            PreparedStatement ps = conn.prepareStatement(query);             
            ResultSet rs = ps.executeQuery(); 
            System.out.println("excute");  
            String password="Fail";    
            while (rs.next()) {
                password = rs.getString("password");                 
                System.out.println(password); 
                break;                   
            }   
            return password;   
        
        } catch (SQLException e) {
        // handle the exception
            System.out.println("Fail");
            System.out.println(e.getErrorCode());
            return "Fail";
        }
       
    }
}