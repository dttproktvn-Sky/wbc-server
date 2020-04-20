package com.singalarity.wbc.server.restapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.singalarity.wbc.server.restapi.apiResult.APIResult;
import com.singalarity.wbc.server.models.UserInfo;

@RestController
public class LoginController {
    @PostMapping("/wbcLogin")
    public APIResult getConfig(@RequestBody UserInfo userInfor){
        System.out.println("UserId: "+userInfor.getUid());
        System.out.println("hassPassword: "+userInfor.getHashPassword());
        return new APIResult(200,"OK");
    }

}
