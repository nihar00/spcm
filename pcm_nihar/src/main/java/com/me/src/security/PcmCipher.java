/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author prakashj
 */
public class PcmCipher {

    private Cipher eCipher;
    private Cipher dCipher;
    final static int ITERATIONS = 13;
    final static byte[] SALT = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x42, (byte) 0x56, (byte) 0x45, (byte) 0xE3, (byte) 0xB3};

    public PcmCipher(String pass,String keyName) throws Exception {
        SecretKey key = getSecretKey(pass, keyName);
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

	private SecretKey getSecretKey(String pass,String keyName)
  	{

  		try {

  			File f = new File("WEB-INF/security/kerberos.jce");
  			FileInputStream is= new FileInputStream(f);

  			KeyStore keystore = KeyStore.getInstance("JCEKS");
  			keystore.load(is, pass.toCharArray());
  			SecretKey k= (SecretKey) keystore.getKey(keyName, pass.toCharArray());
  			return k;

  		} 

  		catch (FileNotFoundException e1) {
  			// TODO Auto-generated catch blockr
  			e1.printStackTrace();
  		} catch (KeyStoreException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		} catch (NoSuchAlgorithmException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		} catch (CertificateException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		} catch (IOException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}catch (UnrecoverableKeyException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

  		return null;
  	}

}