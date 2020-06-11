package com.singalarity.wbc.server.models;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

    private byte[] keyBytes;
    private Cipher cipher;
    private SecretKeySpec key;
    private String deviceID;
    private boolean generateSuccess = false;
    public AESEncryption(String deviceID) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        this.deviceID = deviceID;        
        try {
            DatabaseQuery databaseQuery = new DatabaseQuery(); 
            keyBytes = databaseQuery.getDeviceIDAESKey(deviceID);
            key = new SecretKeySpec(keyBytes, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");          
            generateSuccess = true;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {            
            e.printStackTrace();
        }
    }  
    public String encrypt(String plainText) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext= cipher.doFinal(plainText.getBytes());            
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (BadPaddingException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String decrypt(String cipherText) {
        try {            
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String (plaintext);
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (BadPaddingException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String getDeviceID(){
        return deviceID;
    }
    public boolean getGenerateSuccessful(){
        return generateSuccess;
    }
}