package com.singalarity.wbc.server.models;

public class UserInfo {
    private String uid;
    private String hashPassword;
    private String plaintext;
    private String encrypted;
    public UserInfo(){

    }
    public UserInfo(String uid, String hassPassword,String plaintext, String encrypted){
        this.uid = uid;
        this.hashPassword = hassPassword;
        this.plaintext=plaintext;
        this.encrypted=encrypted;
    }
    public UserInfo (String plaintext, String encrypted){
        this.plaintext=plaintext;
        this.encrypted=encrypted;
    }
    public String getPlaintext(){
        return this.plaintext;
    }
    public String getEncrypted(){
        return this.encrypted;
    }
    public String getUid(){
        return this.uid;
    }
    public String getHashPassword(){
        return this.hashPassword;
    }
}