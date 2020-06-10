package com.singalarity.wbc.server.restapi.requestObject;
import java.util.Base64;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;

import com.singalarity.serverLib.UserInfo;
public class LoginRequest{
    private String deviceID;
    private String userInfoEncode;
    private UserInfo userInfo;
    private boolean isValid;
    public LoginRequest(String deviceID, String userInfoEncode){
        this.deviceID = deviceID;
        this.userInfoEncode = userInfoEncode;
        this.isValid = checkDataValidation();
    }
    private UserInfo retriveUserInfo(){
        try{
            //Todo Decryption 
            byte[] userInfoByte = Base64.getDecoder().decode(userInfoEncode);
            System.out.println(userInfoEncode);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(userInfoByte));    
            UserInfo testUserInfo = (UserInfo) ois.readObject();           
            return testUserInfo;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    private boolean checkDataValidation(){
        if (deviceID == null || userInfoEncode == null){
            return false;
        }
        this.userInfo = retriveUserInfo();
        if (userInfo == null){
            return false;
        }
        return true;
    }

    public String getDeviceID(){
        return this.deviceID;
    }
    public UserInfo getUserInfo(){
        return this.userInfo;
    }   
    public boolean getValidationResult(){
        return this.isValid;
    }
}