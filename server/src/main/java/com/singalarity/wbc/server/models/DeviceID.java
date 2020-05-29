package com.singalarity.wbc.server.models;

public class DeviceID {
    private String deviceID;
    public DeviceID(){
        
    }
    public DeviceID(String deviceID){
        this.deviceID = deviceID;
    }
    public String getDeviceID(){
        return this.deviceID;
    }
}