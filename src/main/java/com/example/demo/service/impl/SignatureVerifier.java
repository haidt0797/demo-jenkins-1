package com.example.demo.service.impl;

import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class SignatureVerifier {
    public static void main(String[] args) throws Exception {




        // Dữ liệu gốc
        String data = "qsx|authtrans|https://beta-service.sacombank.com/khtt/MobileGiftRedeem/GiftRedeemVerify?accessCode=a0556e40-7abb-42bf-ac31-a5683a5748d7&id=e4049f5b-c969-4887-af00-9cf0d0adf464";

        // Public Key Base64 (PKCS#8)
        String publicKeyBase64 = "MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQBOJM8tSTIUcrOBZuTugqWn3cl1sNYXSzc/tHax4FbXmRAzBT9g2ep6j64mezvJufJjTqoxcw4bIPbXhkoA3fpygEbA4AJrfe+xyRElcRmYYm5VLRzJmAjKdZ/qrUTQq1xqsukdOXRT904oziUSYuo/F0FRXsLD1hmLivyWxVUazC6Am881Ml4+lUAP5tto5TtKdhG/veIrQ6wDwFbodg3PtA6LRMCPAzgiwFDhDdjIKPl/DVsMwUwmC0s+8jMOHqksXCUphjPX5frM6SKVzWTIocl7M6+40QjS2hodlQ0lQW/iiGEWv5A+Qt+n+HYgG5AYSYikTFZCtQ9UgIM15mlpAgMBAAE=";

        // Chữ ký (Base64)
        String signatureBase64 = "PSLSoJ5KE8kTCXLFYWcsp6NWoD0lJpf5X5T/0H+mQUkJDO5MbCtWLvGybJMC4OulrvSpo0D5b/xFng1oO7N2Aybdna7QEY6pqYhlovrfJSsz1veaaf0N5PR7lSSe+bsA9Aamq+N+p5UdOtUT4uMWCI4IRIGSG1ERdHS+PzA5enNidtuBnlyhrgQMfwYtOcMD8+doPLort64jlhMnUAUFZEhE5SQT6S7E1i95bXYkWUgm55/WYZ73YX+ckW/AIQcUIpQOIlLeRJTV54VQMHVKUSdlvydFPO9cboxMoG2LwyF5+nsNeK0viFElmE8JAhOcdVoeLNzejZMArF+tQ9k7Ew==";

        // Decode public key
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Prepare signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes("UTF-8"));

        // Verify
        boolean isValid = signature.verify(Base64.getDecoder().decode(signatureBase64));
        System.out.println("✅ Chữ ký " + (isValid ? "HỢP LỆ" : "KHÔNG hợp lệ"));
    }
}
