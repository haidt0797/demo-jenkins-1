package com.example.demo.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class RsaSignVerifyExample {

    public static void main(String[] args) throws Exception {
        String message = "Giao dịch ngân hàng 123456";

        PrivateKey privateKey = loadPrivateKey(null);
        PublicKey publicKey = loadPublicKey(null);

        // Ký dữ liệu
        String signature = signData(message, privateKey);
        System.out.println("Chữ ký (base64): " + signature);

        // Giải ký
        boolean isValid = verifySignature(message, signature, publicKey);
        System.out.println("Chữ ký hợp lệ? " + isValid);
    }

    public static String signData(String data, PrivateKey privateKey) throws Exception {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update(data.getBytes("UTF-8"));
        byte[] signature = signer.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verifySignature(String data, String base64Signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes("UTF-8"));
        byte[] signature = Base64.getDecoder().decode(base64Signature);
        return verifier.verify(signature);
    }

    public static PrivateKey loadPrivateKey(String filename) throws Exception {
        String key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdDG7z0kYKN6ODJcHYkOrGT7WrSZg6oJ8AjlpVddynTWCxIIpA7IWCO5rQQhUCrDs5Sb/Ul6AYzbMP4EHOhHj1pIp03Q9J+QJ1HzXbfxxTcVld4bm16nVSsMNDeTRgVM5HB+6Vof7mjAPrHXK1FO8O0PsN2vvaAP9tfu/1UDtbQDUSP/SutpEPseXK6KjKAhGN8ClaT0xCzPaWJHqsUiTK8Le3OQ/LoF729+jBAnCXNMt4m3p9n8dtHxzJzUW7wevEGfq4Tuss8AkNLD3ByGqNPRp+wCsKqppZkhHTSh7THPURFno83jeC3CpyBsY0bzLwCjChly8gCQb5cC2bnvtlAgMBAAECggEARyn4KPISnNSjem0kjr70M9KwzqI1ofNguX7H/ku/JeyRMRI+s7a5tM7PkykunhxvZcE+dNYUTMkBJ+OFzlAHA8CAoP3+zJVSgMIYxJKvwjvts/XyEjXg3gyNHskQa8piApvch991QwEVUXfxn46R2RPnf1rInvuuwu4vS+kETI9w8H4EcObrKW8fWKHBlHjFFo389Pi7gqI7YePBB5PrbiKf+3dtfrCeiOuRR4vVnW86IokZKDPQxj0jL+LngpM3f/lcx5yfae8+b2sltKAxF8tBZO6qnIDu5buJXgJbSdxZxyc1gCfJGq+i5RlcrMs9vjY4HaZmNClpVBomx0YjrQKBgQDOj39VMfb7dojatWzNc0XBxleN9RRQL/ym+iapapPRr0p0M7fffRnDGtgljCfpoKjuME78aZM79EYVS0BINrMj5YL8eOpQXKEOe5BMg4acW+xKxpFKsUetFdqleU+rSeeGsJsrBpF67D50HNZOoHAajA1zSPR/BU5jee9hM4h/JwKBgQDCozVndJTuGdNx0u72kZWD2BYW+lKpJpUfiEaDkSEQCD6VzaC/kXHq/Y/M1Z+Cs8hf6wG/Zq80DVfxmSt0qH1sRHm6oSLfvNRQ4Q7v+lrkqohgeu9FNwm+za6nywUe7MTRP57ukM+9F8caNgQH6HojYuSOfKNyoKRDtl4pqI1IkwKBgQCL7y7GGplFdeH0uJ/C92q0gZfi9bsTL88rl6AFecw2a1g3LKpOX0PKpy7zEOYhIdQPuO5plWRHY47u8hHUWio2EVARliC23GnHk/IHw7q3xpZneeoH/d2e5oU/QNW2JtUQrCgSl9qKuez9YGPW1F3dvi+GgFASlRer0HlZEj5QVQKBgBYvYDDwmuEg1HcO4en84hvrD2mDEtTw7uJvOApbCne3WNreW0o6m897I17noozVTSuRr9tPKv014CZLnSZo5hLnIzDgmQcDkPe/D2LPGHkj7vOAT7GF+nhjHVtGysihmDV5mPX8T5FwGmOkcIUuPG24IfETx9WS9eY57TW2XpQ9AoGAU+9DitypgyBp6p7vLF+pjfnXF2+O0KPN26DEn0B2iQq01DzQ3FhutEkHfRF6Q180hTW8e7P2UWH/NzPOvYCF/PsAfcQP88b4twvd6rWFcpdALu74r9K1LIeygrxrTTzAdU65LWzuViJX2mtfeqvpkM+RdrfFD5sR8ZdbM8AZPTk=";

        byte[] keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public static PublicKey loadPublicKey(String filename) throws Exception {
        String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnQxu89JGCjejgyXB2JDqxk+1q0mYOqCfAI5aVXXcp01gsSCKQOyFgjua0EIVAqw7OUm/1JegGM2zD+BBzoR49aSKdN0PSfkCdR81238cU3FZXeG5tep1UrDDQ3k0YFTORwfulaH+5owD6x1ytRTvDtD7Ddr72gD/bX7v9VA7W0A1Ej/0rraRD7HlyuioygIRjfApWk9MQsz2liR6rFIkyvC3tzkPy6Be9vfowQJwlzTLeJt6fZ/HbR8cyc1Fu8HrxBn6uE7rLPAJDSw9wchqjT0afsArCqqaWZIR00oe0xz1ERZ6PN43gtwqcgbGNG8y8AowoZcvIAkG+XAtm577ZQIDAQAB";

        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}
