package com.singalarity.wbc.server.models;
import com.singalarity.wbc.server.models.DatabaseQuery;
import com.singalarity.serverLib.UserInfo;
import java.io.Serializable;
public class UserInfoManagement implements Serializable {
    private UserInfo userInfo; 
    public UserInfoManagement(UserInfo userInfo){
        this.userInfo = userInfo;
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
            String databasePassword = databaseQuery.getPasswordByUsername(userInfo.getUid());
            if (databasePassword.equals(userInfo.getHashPassword())){
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
            return databaseQuery.registerUser(userInfo.getUid(), userInfo.getHashPassword());
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public UserInfo getUserInfo(){
        return this.userInfo;
    }
}