package com.singalarity.wbc.server.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.text.html.parser.DTD;

import com.singalarity.wbc.server.models.DatabaseConstrant;
public class DatabaseQuery {
    String sqlFindUser = "SELECT * FROM mobileUser WHERE username = ";
    String connectionUrl = "jdbc:mysql://localhost:3306/db";
    public Connection conn;
    public DatabaseQuery() throws Exception {      
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        try{           
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + DatabaseConstrant.mySQLServerAddress + DatabaseConstrant.dbName,
                    DatabaseConstrant.dbUser, DatabaseConstrant.dbPassword);            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw e;
        }       
    }
    public String getPasswordByUsername(String username) throws Exception {        
        try {            
            String checkPasswordByUsernameQuerry = DatabaseConstrant.findUserQuery+"'"+ username+"'";
            System.out.println(checkPasswordByUsernameQuerry);            
            PreparedStatement ps = this.conn.prepareStatement(checkPasswordByUsernameQuerry);             
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
            throw e;
        }       
    }
    public Boolean registerUser(String username, String password){
        //String addUserToDBQuery = DatabaseConstrant.addUserQuery;
        ResultSet rs = null;
        System.out.println(DatabaseConstrant.addUserQuery);
        try {
            PreparedStatement ps = this.conn.prepareStatement(DatabaseConstrant.addUserQuery,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,username);
            ps.setString(2,password);
            int rowAffected = ps.executeUpdate();
            if(rowAffected == 1){
                rs = ps.getGeneratedKeys();
                if (rs.next()){
                    System.out.println(rs.getInt(1));
                }
                return true;
            } else{
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return false;            
        }        
    }
}