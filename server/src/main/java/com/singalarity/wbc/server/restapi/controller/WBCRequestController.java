package com.singalarity.wbc.server.restapi.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.singalarity.wbc.server.restapi.apiResult.APIResult;
import com.singalarity.wbc.server.restapi.apiResult.WBCRequestAPIResult;
import com.singalarity.wbc.server.models.DeviceID;
import com.singalarity.wbc.server.models.WBCManagement;
import com.singalarity.wbc.server.models.DatabaseQuery;

@RestController
public class WBCRequestController {
    @PostMapping("/wbcRequest")
    public APIResult doWBCRequest(@RequestBody DeviceID deviceID ){
        if (deviceID.getDeviceID()!=null){
            System.out.println(deviceID.getDeviceID());            
            WBCManagement wbcManagement = new WBCManagement(deviceID.getDeviceID());
            if (wbcManagement.getGenerateSuccessful()){
                try{
                    DatabaseQuery databaseQuery = new DatabaseQuery(); 
                    databaseQuery.insertKeyAndDeviceID(deviceID.getDeviceID(), wbcManagement.getAESKey());
                    System.out.println(wbcManagement.byteToString(wbcManagement.getAESKey()));                 
                    return new WBCRequestAPIResult(200,"Okey",wbcManagement.getWBCFileString());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return new APIResult(404,"fail");
                }              
            }
            else return new APIResult(404,"fail");          
        }
        return new APIResult(404,"fail");
    }
}