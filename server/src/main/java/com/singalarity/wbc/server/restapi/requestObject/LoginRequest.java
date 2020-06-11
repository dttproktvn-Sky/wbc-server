package com.singalarity.wbc.server.restapi.requestObject;
import java.util.Base64;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import com.singalarity.wbc.server.models.AESEncryption;
import com.singalarity.serverLib.UserInfo;
public class LoginRequest{
    private String deviceID;
    private String userInfoEncode;
    private UserInfo userInfo;   
    private AESEncryption aesEncryption;
    public LoginRequest(String deviceID, String userInfoEncode){
        this.deviceID = deviceID;
        this.userInfoEncode = userInfoEncode;       
    }
    private UserInfo retriveUserInfo(){
        try{
            //Todo Decryption             
            String userInfoDecrypt = "";           
            userInfoDecrypt = this.aesEncryption.decrypt(userInfoEncode);
            byte[] userInfoByte = Base64.getDecoder().decode(userInfoDecrypt);           
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(userInfoByte));    
            UserInfo testUserInfo = (UserInfo) ois.readObject();           
            return testUserInfo;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean checkDataValidation(){
        if (deviceID == null || userInfoEncode == null){
            return false;
        } else{
            aesEncryption = new AESEncryption(deviceID);
            if (!aesEncryption.getGenerateSuccessful()) return false;
            userInfo = retriveUserInfo();
            if (userInfo == null){
                return false;
            }
            return true;
        }
        
    }

    public String getDeviceID(){
        return this.deviceID;
    }
    public UserInfo getUserInfo(){
        return this.userInfo;
    }  
}