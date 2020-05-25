package com.singalarity.wbc.server.restapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.singalarity.wbc.server.restapi.apiResult.APIResult;
import com.singalarity.wbc.server.models.UserInfo;
import com.singalarity.wbc.server.models.AESEncryption;
@RestController
public class LoginController {
    @PostMapping("/wbcLogin")
    public APIResult doLogin(@RequestBody UserInfo userInfor){
        if (userInfor.getUid()!= null){
            System.out.println("UserId: "+userInfor.getUid());
            System.out.println("hassPassword: "+userInfor.getHashPassword());            
            AESEncryption aesEncryption= new AESEncryption();
            byte[] keyBytes= new byte[]{(byte)0x4a, (byte)0x2d, (byte)0x1d, (byte)0x65, (byte)0xb5,
                (byte)0xb1, (byte)0xe2, (byte)0x2d, (byte)0xfc, (byte)0xea,
                (byte)0xa0, (byte)0x65, (byte)0xd7, (byte)0x63, (byte)0x21, (byte)0x67};
            aesEncryption.createKey(keyBytes);            
            if (userInfor.isTruePassword()){
                return new APIResult(200,"OK");
            }
            else return new APIResult(403,"false");
            
        }
        else return new APIResult(404,"fail");
    }
    @PostMapping ("/wbccryption")
    public APIResult getCryptionInfo(@RequestBody UserInfo userInfo){
        System.out.println("data to encrypt:"+ userInfo.getHashPassword());
        return new APIResult(404,"fail");
    }
}
