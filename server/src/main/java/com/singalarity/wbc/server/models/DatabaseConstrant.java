package com.singalarity.wbc.server.models;

public class DatabaseConstrant {
    public static String mySQLServerAddress = "localhost:3306/";
    public static String dbName = "db";
    public static String dbUser = "user";
    public static String dbPassword = "password";
    public static String findUserQuery = "SELECT * FROM mobileUser WHERE username =";
    public static String addUserQuery = "INSERT INTO mobileUser (username,password) VALUES (?,?)";    
    public static String addDeviceIDAESKey = "INSERT INTO deviceIDAESKey (deviceID, AESKey) VALUES(?,?)";
    public static String findDeviceID = "SELECT * FROM deviceIDAESKey WHERE deviceID =";
}