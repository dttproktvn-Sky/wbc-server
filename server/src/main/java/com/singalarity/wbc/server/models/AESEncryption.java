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

    byte[] keyBytes;
    Cipher cipher;
    SecretKeySpec key;

    public AESEncryption() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createKey(final byte[] keyBytes) {
        this.keyBytes = keyBytes;
        key = new SecretKeySpec(keyBytes, "AES");
    }

    /*public byte[] encrypt(final byte[] plainText) {
        try {

            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
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

    public byte[] decrypt(final byte[] plainText) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(plainText);
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
*/
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
}