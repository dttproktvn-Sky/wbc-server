package com.singalarity.wbc.server.models;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.Base64;

import cz.muni.fi.xklinec.whiteboxAES.WBCStringCryption;
import cz.muni.fi.xklinec.whiteboxAES.AESEncryption;
import cz.muni.fi.xklinec.whiteboxAES.generator.Generator;
import cz.muni.fi.xklinec.whiteboxAES.generator.ExternalBijections;
import cz.muni.fi.xklinec.whiteboxAES.WBCStringCryption;

public class WBCManagement {
    private byte[] AESKey =  new byte[16];
    private boolean generateSuccessful = true;
    private WBCStringCryption wbcStringEncryption;
    private String wbcFileString;
    public WBCManagement(){
    
    }
    public WBCManagement(String deviceID){
        generateAESKey();
        if (generateSuccessful){
            generateWBC();
            if (generateSuccessful){
                CreateFileWBC(deviceID);
                if (generateSuccessful){
                    ExtractWBCFile(deviceID);
                }
               
            }
        }
    }
    public void ExtractWBCFile(String deviceID){
        try{
            File file = new File("wbcfile/"+deviceID+"EncryptionWBC"); 
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                   
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                this.generateSuccessful = false;
                return;
            }
            byte[] bytes = bos.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(bytes);
            this.wbcFileString = encoded;
           // System.out.println(encoded);
            fis.close();
            //-------------------------------FINISH SERVER SIDE --------------------------------//
            byte[] decodebytes = Base64.getDecoder().decode(encoded);
            // Write To File
            File someFile = new File("MobileFile");
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(decodebytes);
            fos.flush();
            fos.close();

            FileInputStream mobileFile = new FileInputStream("MobileFile"); 
            ObjectInputStream mobileIn = new ObjectInputStream(mobileFile);
           
            WBCStringCryption deserializedWBCEnc = (WBCStringCryption) mobileIn.readObject();            
            mobileFile.close(); 
            mobileIn.close(); 


        } catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            this.generateSuccessful = false;
            return;
        }
        
    }
    private void generateAESKey(){
        try{
            byte[] byteArray = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(byteArray);
            this.AESKey = byteArray;
        }catch (Exception e){
            System.out.println(e.getMessage());
            this.generateSuccessful = false;
        }
        
    }
    private void generateWBC(){
        try{
            Generator gEnc = new Generator();      
            ExternalBijections extc = new ExternalBijections();
            gEnc.generateExtEncoding(extc, Generator.WBAESGEN_EXTGEN_ID);
            gEnc.setUseIO04x04Identity(true);
            gEnc.setUseIO08x08Identity(true);
            gEnc.setUseMB08x08Identity(true);
            gEnc.setUseMB32x32Identity(true);
            gEnc.generate(true, AESKey, 16, extc);
            this.wbcStringEncryption = new WBCStringCryption(gEnc.getAESi(), true);           
        } catch (Exception e){
            System.out.println(e.getMessage());
            this.generateSuccessful = false;
        }        
    }
    public void CreateFileWBC(String deviceID){
        try{
            FileOutputStream file = new FileOutputStream("wbcfile/"+deviceID+"EncryptionWBC");
            ObjectOutputStream out = new ObjectOutputStream(file); 
            out.writeObject(wbcStringEncryption);
            out.close(); 
            file.close();               
            System.out.println("Object has been serialized");        
            } catch(IOException e){
                System.out.println("2. IOException is caught"); 
                this.generateSuccessful = false;
            }
    }
    public String byteToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("0x%02X", bytes[i] & 0xff));
            if ((i + 1) != length) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    public byte[] getAESKey(){
        return this.AESKey;
    }
    public boolean getGenerateSuccessful(){
        return this.generateSuccessful;
    }
    public WBCStringCryption getWbcStringCryption(){
        return this.wbcStringEncryption;
    }
    public String getWBCFileString(){
        return this.wbcFileString;
    }
}