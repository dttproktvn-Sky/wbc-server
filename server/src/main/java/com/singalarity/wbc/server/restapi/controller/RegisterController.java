package com.singalarity.wbc.server.restapi.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.singalarity.wbc.server.restapi.apiResult.APIResult;
import com.singalarity.wbc.server.models.UserInfo;

@RestController
public class RegisterController{
    @PostMapping("/wbcRegister")
    public APIResult doRegister(@RequestBody UserInfo userInfor){
        if (userInfor.getUid()!= null){
            System.out.println("UserId: "+userInfor.getUid());
            System.out.println("hassPassword: "+userInfor.getHashPassword());   
            Boolean registerResult = userInfor.doRegisterUser();
            if (registerResult){
                return new APIResult(200,"OK");
            }else{
                return new APIResult(405,"RegisterFail");
            }
        }else{
            return new APIResult(404,"fail");
        }
    }

}