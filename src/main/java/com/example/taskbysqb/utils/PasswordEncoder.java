package com.example.taskbysqb.utils;


import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String encode(String username, String password){
        try {
            MessageDigest digest  = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update((username+password).getBytes(StandardCharsets.UTF_8), 0, (username+password).length());
            return DatatypeConverter.printHexBinary(digest.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Is not working");
        }
    }

    public static void compareCredentials(String savedPassword, String username, String password){
        if (!savedPassword.equals(encode(username, password))){
            throw new RuntimeException("Unauthorized");
        }
    }
}
