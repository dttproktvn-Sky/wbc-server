package com.singalarity.wbc.server.restapi.apiResult;
import cz.muni.fi.xklinec.whiteboxAES.WBCStringCryption;
public class WBCRequestAPIResult extends APIResult{
    private String wbcStringFile;
    public WBCRequestAPIResult(){
    }
    public WBCRequestAPIResult(int code, String message){
        super(code,message);
    }
    public WBCRequestAPIResult(int code, String message, String wbcStringFile){
        super(code,message);
        this.wbcStringFile = wbcStringFile;
    }
    public String getwbcFileString(){
        return this.wbcStringFile;
    }

}