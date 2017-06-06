package com.czm.core.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * Created by CAOZHIHUI on
 * 2016/1/19.
 */
public class PwdUtil {

    public static final String pwd = "iHCMEpSJS";

//    public static void main(String[] args) {
////       String passwordSalt = generatePasswordSalt();
////        System.out.println(passwordSalt);
//        System.out.println(getPasswordHash("534222", "pF0OpQq9"));
//       String encryptedString = encryptString("3", pwd);
//     System.out.println(encryptedString);
////        String passwordHash = getPasswordHash("12345678", pwd);
////        System.out.println(passwordHash);
//        //System.out.println(verifyPassword("12345678", passwordHash, pwdSalt));
////        System.out.println(decryptString("nbMyMZ2nQiPxB0QacGZaaA==", pwd));
////        String encryptedString = encryptString("Cz7sKuY@#RPvo&#K", "nAEZt%hvLkHy5Y$R");
////        System.out.println(encryptedString);
////        System.out.println(decryptString(encryptedString, "nAEZt%hvLkHy5Y$R"));
////        System.out.println(decryptString("B1xX5CkERc//0EiBLSimebR5QZ5T+KJeK3pvwhlZCAU=", "nAEZt%hvLkHy5Y$R"));
////        System.out.println(encryptString("root", passwordSalt));
//    }

    public static String encryptString(String content, String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(password.getBytes(StandardCharsets.UTF_8)));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptString(String content, String password) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes(StandardCharsets.UTF_8));
            _generator.init(128, secureRandom);
            SecretKey secretKey = _generator.generateKey();

            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyPassword(String password, String passwordHash, String passwordSalt) {
        return !(password == null || passwordHash == null || passwordSalt == null) && passwordHash.equalsIgnoreCase(getPasswordHash(password, passwordSalt));
    }

    public static String getPasswordHash(String password, String passwordSalt) {
        byte[] passwordSaltBytes = Base64.getDecoder().decode(passwordSalt);
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] bytes = Arrays.copyOf(passwordBytes, passwordBytes.length + passwordSaltBytes.length);
        System.arraycopy(passwordSaltBytes, 0, bytes, passwordBytes.length, passwordSaltBytes.length);
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        digest.update(bytes);
        byte messageDigest[] = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append("0");
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }

    public static String generatePasswordSalt() {
        Random rnd = new Random();
        byte[] bytes = new byte[6];
        rnd.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

}
