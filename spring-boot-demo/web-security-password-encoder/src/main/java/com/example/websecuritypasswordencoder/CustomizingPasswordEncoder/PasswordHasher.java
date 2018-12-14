package com.example.websecuritypasswordencoder.CustomizingPasswordEncoder;


import java.util.Base64;
import java.util.Random;


public class PasswordHasher {

    public static boolean VerifyHashedPassword(String hashedPassword, String providedPassword) throws Exception {
        if (hashedPassword == null) {
            throw new RuntimeException("hashedPassword");
        }
        if (providedPassword == null) {
            throw new RuntimeException("providedPassword");
        }

        byte[] array = Base64.getDecoder().decode(hashedPassword);
        if (array.length != 0) {
            switch (array[0]) {
                case 0:
                    return VerifyHashedPasswordV2(array, providedPassword);
                case 1:

                    return VerifyHashedPasswordV3(array, providedPassword);
                default:
                    return false;
            }
        }
        return false;
    }


    public static String HashPasswordV3(String password) throws Exception {
        byte[] hashedPwd = HashPasswordV3(password, 10000, 16, 32);
        return Base64.getEncoder().encodeToString(hashedPwd);
    }

    //"HmacSHA256"
    private static byte[] HashPasswordV3(String password, int iterCount, int saltSize, int numBytesRequested) throws Exception {
        byte[] array = new byte[saltSize];
        new Random().nextBytes(array);
        byte[] array2 = PBKDF2.deriveKey(password.getBytes(), array, "HmacSHA256", iterCount, numBytesRequested);

        byte[] array3 = new byte[13 + array.length + array2.length];
        array3[0] = 1;

        WriteNetworkByteOrder(array3, 1, 1);//1 "HmacSHA256"
        WriteNetworkByteOrder(array3, 5, iterCount);
        WriteNetworkByteOrder(array3, 9, saltSize);

        System.arraycopy(array, 0, array3, 13, array.length);
        System.arraycopy(array2, 0, array3, 13 + saltSize, array2.length);

        return array3;

    }

    private static void WriteNetworkByteOrder(byte[] buffer, int offset, int value) {
        buffer[offset] = (byte) (value >> 24);
        buffer[offset + 1] = (byte) (value >> 16);
        buffer[offset + 2] = (byte) (value >> 8);
        buffer[offset + 3] = (byte) value;
    }


    private static boolean VerifyHashedPasswordV2(byte[] hashedPassword, String password) throws Exception {
        if (hashedPassword.length != 49) {
            return false;
        }
        byte[] array = new byte[16];
        System.arraycopy(hashedPassword, 1, array, 0, array.length);
        byte[] array2 = new byte[32];
        System.arraycopy(hashedPassword, 1 + array.length, array2, 0, array2.length);
        return ByteArraysEqual(PBKDF2.deriveKey(password.getBytes(), array, "HmacSHA1", 1000, 32), array2);
    }

    private static boolean VerifyHashedPasswordV3(byte[] hashedPassword, String password) {
        int iterCount = 0;
        try {
            String sha = "HmacSHA1";

            int val = (int) ReadNetworkByteOrder(hashedPassword, 1);

            if (val == 1) sha = "HmacSHA256";

            if (val == 2) sha = "HmacSHA512";

            iterCount = (int) ReadNetworkByteOrder(hashedPassword, 5);
            int num = (int) ReadNetworkByteOrder(hashedPassword, 9);
            if (num >= 16) {
                byte[] array = new byte[num];
                System.arraycopy(hashedPassword, 13, array, 0, array.length);
                int num2 = hashedPassword.length - 13 - array.length;
                if (num2 >= 16) {
                    byte[] array2 = new byte[num2];
                    System.arraycopy(hashedPassword, 13 + array.length, array2, 0, array2.length);
                    return ByteArraysEqual(PBKDF2.deriveKey(password.getBytes(), array, sha, iterCount, num2), array2);
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean ByteArraysEqual(byte[] a, byte[] b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < a.length; i++) {
            flag &= (a[i] == b[i]);
        }
        return flag;
    }

    private static int ReadNetworkByteOrder(byte[] buffer, int offset) {
        return (int) ((buffer[offset] << 24) | (buffer[offset + 1] << 16) | (buffer[offset + 2] << 8) | buffer[offset + 3]);
    }
}
