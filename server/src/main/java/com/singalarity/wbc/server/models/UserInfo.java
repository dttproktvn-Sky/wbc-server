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
        DatabaseQuery databaseQuery = new DatabaseQuery();
        String databasePassword = databaseQuery.checkPassword(this.uid);
        if (databasePassword.equals(this.hashPassword)){
            return true;
        }
        return false;
    }
    public String getUid(){
        return this.uid;
    }
    public String getHashPassword(){
        return this.hashPassword;
    }
}