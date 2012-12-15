/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.security;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

/**
 *
 * @author prakashj
 */
public class HashGenerator {

    public enum MessageDigestType {

        MD5 {

            @Override
            public String toString() {
                return "MD5";
            }
        },
        SHA_1 {

            @Override
            public String toString() {
                return "SHA-1";
            }
        },
        SHA_256 {

            @Override
            public String toString() {
                return "SHA-256";
            }
        },
        SHA_512 {

            @Override
            public String toString() {
                return "SHA-512";
            }
        }
    }

    private static byte[] getSalt() {
            byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03};
            return salt;
    }

    public static String getClientKey(String password) {
        String clientKey = getHashValue(password);
        return clientKey;
    }
    
    public static String getSessionHash(String inputSessionId) {
        String sessionId = getHashValue(inputSessionId);
        return sessionId;
    }
    
    private static String getHashValue(String inputStr) {
        try {
            //get digest/hash value
            MessageDigest digest = MessageDigest.getInstance(MessageDigestType.SHA_256.toString());
            digest.reset();
            digest.update(HashGenerator.getSalt());

            byte[] bOutput = digest.digest(inputStr.getBytes());

            //iterate 101 times
            for (int i = 0; i < 101; i++) {
                digest.reset();
                bOutput = digest.digest(bOutput);
            }

            //convert digest and salt to string
            String strOutput = new BASE64Encoder().encode(bOutput);
            return strOutput;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
