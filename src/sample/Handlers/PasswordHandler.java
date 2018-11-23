package sample.Handlers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler {
    public static String MakeHash(String password, String salt) throws NoSuchAlgorithmException{
        return MakeHash(password + salt);
    }
    public static String MakeHash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

        byte[] digest = messageDigest.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for(byte b: digest){
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
