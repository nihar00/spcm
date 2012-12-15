/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.security;

import java.io.Serializable;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author prakashj
 */
public class DesEncrypter {

    private Cipher eCipher;
    private Cipher dCipher;
    final static int ITERATIONS = 13;
    final static byte[] SALT = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x42, (byte) 0x56, (byte) 0x45, (byte) 0xE3, (byte) 0xB3};

    public DesEncrypter(byte[] passPhrase) throws Exception {
        char [] phrase = new BASE64Encoder().encode(passPhrase).toCharArray();
        
        // to resolve : java.security.spec.InvalidKeySpecException: Password is not ASCII
        for(int i=0; i<phrase.length; i++) {
            if(phrase[i] == '\n') {
                phrase[i] = 'n';
            }
        }
        
        KeySpec keySpec = new PBEKeySpec(phrase, SALT, ITERATIONS);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
        eCipher = Cipher.getInstance(key.getAlgorithm());
        dCipher = Cipher.getInstance(key.getAlgorithm());

        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATIONS);

        eCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        dCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
    }

    public String encrypt(String str) throws Exception {
        return new BASE64Encoder().encode(eCipher.doFinal(str.getBytes()));
    }

    public byte[] encrypt(byte[] data) throws Exception {
        return eCipher.doFinal(data);
    }

    public String decrypt(String str) throws Exception {
        return new String(dCipher.doFinal(new BASE64Decoder().decodeBuffer(str)));
    }

    public byte[] decrypt(byte[] data) throws Exception {
        return dCipher.doFinal(data);
    }
    
    public SealedObject encrypt(Serializable obj) throws Exception {
        return new SealedObject(obj, eCipher);
    }

    public Object decrypt(SealedObject obj) throws Exception {
        return obj.getObject(dCipher);
    }
}