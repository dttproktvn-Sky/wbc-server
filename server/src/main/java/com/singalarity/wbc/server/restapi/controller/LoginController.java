package com.singalarity.wbc.server.restapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.singalarity.wbc.server.restapi.apiResult.APIResult;
import com.singalarity.serverLib.UserInfo;
import com.singalarity.wbc.server.models.UserInfoManagement;
import com.singalarity.wbc.server.restapi.requestObject.LoginRequest;
@RestController
public class LoginController {
    @PostMapping("/wbcLogin")
    public APIResult doLogin(@RequestBody LoginRequest loginRequest){
        
        boolean loginRequestValid = loginRequest.checkDataValidation();
        if (!loginRequestValid){
            return new APIResult(404,"Login request is not valid");
        }
        UserInfoManagement userInfoManagement = new UserInfoManagement(loginRequest.getUserInfo());                               
        if (userInfoManagement.isTruePassword()){
            return new APIResult(200,"OK");
        }
        else return new APIResult(403,"false");               
    }
    
}
