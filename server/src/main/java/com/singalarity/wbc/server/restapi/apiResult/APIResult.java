package com.singalarity.wbc.server.restapi.apiResult;
import java.util.Map;
public class APIResult {
    private int code;
    private String message;
    public APIResult(){

    }
    
    public APIResult(int code, String message){
        this.code = code;
        this.message = message;
    }
    public void setCode(int code){
        this.code = code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public int getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}