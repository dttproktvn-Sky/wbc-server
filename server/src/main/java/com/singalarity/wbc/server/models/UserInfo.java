package com.singalarity.wbc.server.models;
import com.singalarity.wbc.server.models.DatabaseQuery;
public class UserInfo {
    private String uid;
    private String hashPassword; 
    public UserInfo(){

    }
    public UserInfo(String uid, String hassPassword){
        this.uid = uid;
        this.hashPassword = hassPassword;       
    }
    public Boolean isTruePassword(){
        DatabaseQuery databaseQuery;
        try{
            databaseQuery = new DatabaseQuery();           
        } catch (Exception e){
            System.out.println("Connect to DB fail");
            return false;  
        }
        try{
            String databasePassword = databaseQuery.getPasswordByUsername(this.uid);
            if (databasePassword.equals(this.hashPassword)){
                return true;
            }
            return false;
        } catch (Exception e){
            System.out.println("SQL Querry fail");
            return false;
        }       
    }
    public Boolean doRegisterUser(){
        DatabaseQuery databaseQuery;
        try{
            databaseQuery = new DatabaseQuery();           
        } catch (Exception e){
            System.out.println("Connect to DB fail");
            return false;  
        }
        try{
            return databaseQuery.registerUser(this.uid, this.hashPassword);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public String getUid(){
        return this.uid;
    }
    public String getHashPassword(){
        return this.hashPassword;
    }
}