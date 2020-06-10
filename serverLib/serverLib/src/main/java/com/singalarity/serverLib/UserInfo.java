package com.singalarity.serverLib;
import java.io.Serializable;

public class UserInfo implements Serializable{
    private String uid;
    private String hashPassword; 
    public UserInfo(String uid, String hashPassword){
        this.uid = uid;
        this.hashPassword = hashPassword;
    }
    public String getUid(){
        return uid;
    }
    public String getHashPassword(){
        return hashPassword;
    }

}