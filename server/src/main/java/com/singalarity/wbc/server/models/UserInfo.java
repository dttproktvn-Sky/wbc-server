package com.singalarity.wbc.server.models;

public class UserInfo {
    private String uid;
    private String hashPassword;
    public UserInfo(){

    }
    public UserInfo(String uid, String hassPassword){
        this.uid = uid;
        this.hashPassword = hassPassword;
    }
    public String getUid(){
        return this.uid;
    }
    public String getHashPassword(){
        return this.hashPassword;
    }
}