package com.singalarity.wbc.server.restapi.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.singalarity.wbc.server.restapi.apiResult.APIResult;
import com.singalarity.wbc.server.restapi.requestObject.LoginRequest;
import com.singalarity.wbc.server.models.UserInfoManagement;
import com.singalarity.serverLib.UserInfo;

@RestController
public class RegisterController{
    @PostMapping("/wbcRegister")
    public APIResult doRegister(@RequestBody LoginRequest loginRequest){
        boolean loginRequestValid = loginRequest.checkDataValidation();
        if (!loginRequestValid){
            return new APIResult(404,"request data is not valid");
        }
        UserInfoManagement userInfoManagement = new UserInfoManagement(loginRequest.getUserInfo());
        System.out.println(userInfoManagement.getUserInfo().getUid());
        System.out.println(userInfoManagement.getUserInfo().getHashPassword());
        boolean registerSuccess = userInfoManagement.doRegisterUser();
        if (registerSuccess){
            return new APIResult(200,"0k");
        }
        else return new APIResult(404,"fail");
    }
}