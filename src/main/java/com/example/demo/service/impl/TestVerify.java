package com.example.demo.service.impl;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;

public class TestVerify {

    public static void main(String[] args) throws Exception {
//        String dataToVerify = "https://beta-service.sacombank.com/khtt/GreenPoint/DonatePay?accessCode=6ee64092-ea8b-4668-b71e-eb009c906bae&lang=vi&amt=50000&co2=232,03Kg&result=1";
//        String signature = "Si2IC3k5U%2BhIoRtunNEGO5e17dYW3h1aMihklEXnZWYpI4UlDplyXipm0NNdbRoR2%2BcpsFH76qh%2BImc%2BEoXSrdf%2F0X7VDiMu0by8wtEvxu2u8R4R1OWRRbcLxnsfk0z09GkEV5RIwep3Uca3678cegOPZ1F%2Bpdkr7sahL3p0A2DWbGyuFRlmfQCiKtCUlxLvWvds%2BoyOU7YgxAJ5Si5VsQi5FoDxTH6hTHN9AXac%2B8PjHEuUpSllnFS2Kwf9FtV7XYeJKMEperS4F%2FjanygmCKNpfKI2xH8z4WhkmEbPrGJ%2FxBJbtRkXiQjvKQE6W6pZs4iFta6QQ7QDUdVTCgeg1A%3D%3D";
//        signature = URLDecoder.decode(signature, StandardCharsets.UTF_8);
//        String publicKeys = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnQxu89JGCjejgyXB2JDq\n" +
//                "xk+1q0mYOqCfAI5aVXXcp01gsSCKQOyFgjua0EIVAqw7OUm/1JegGM2zD+BBzoR4\n" +
//                "9aSKdN0PSfkCdR81238cU3FZXeG5tep1UrDDQ3k0YFTORwfulaH+5owD6x1ytRTv\n" +
//                "DtD7Ddr72gD/bX7v9VA7W0A1Ej/0rraRD7HlyuioygIRjfApWk9MQsz2liR6rFIk\n" +
//                "yvC3tzkPy6Be9vfowQJwlzTLeJt6fZ/HbR8cyc1Fu8HrxBn6uE7rLPAJDSw9wchq\n" +
//                "jT0afsArCqqaWZIR00oe0xz1ERZ6PN43gtwqcgbGNG8y8AowoZcvIAkG+XAtm577\n" +
//                "ZQIDAQAB";
//        PublicKey publicKey = getPublicKey(publicKeys);
//        boolean isValid = verifySignature(publicKey, dataToVerify, signature);
//        System.out.println(isValid);
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().minusMinutes(15));
        System.out.println((LocalDateTime.now().minusMinutes(14)).isBefore(LocalDateTime.now().minusMinutes(15)));
    }

    public static int parseInt(String lString, int defaultValue) {
        try {
            return Integer.parseInt(lString);
        } catch (Exception e) {
            System.out.println("parseInt - ERROR: " + e.getMessage());
            return defaultValue;
        }
    }

    public static PublicKey getPublicKey(String pemPublicKey) throws Exception {
        pemPublicKey = replaceLineBreak(pemPublicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        byte[] keyData = decodeBase64(pemPublicKey);
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(keyData);
        return kf.generatePublic(keySpecX509);
    }

    public static boolean verifySignature(PublicKey pubKey, String data, String signature) throws Exception {
        Signature instance = Signature.getInstance("SHA256withRSA");
        instance.initVerify(pubKey);
        instance.update(data.getBytes(StandardCharsets.UTF_8));
        signature = replaceLineBreak(signature);
        byte[] signData = decodeBase64(signature);
        return instance.verify(signData);
    }

    public static String replaceLineBreak(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.trim().replaceAll("\\n", "");
    }

    public static byte[] decodeBase64(String base64Data) {
        if (base64Data == null || base64Data.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(base64Data);
    }

}