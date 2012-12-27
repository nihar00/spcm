/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author prakashj
 */
public class KeyStoreWrapper {
    
    private KeyStore ks;
    private String filePath;
    private String storePassword;
    private String keyStoreType;

    public KeyStoreWrapper(String filePath, String storePassword, String keyStoreType) {
        this.filePath = filePath;
        this.storePassword = storePassword;
        this.keyStoreType = keyStoreType;
        
        loadKeyStore();
    }
    
    public KeyStore getKeyStore() {
        return ks;
    }    
    
    private KeyStore loadKeyStore() {
        char[] password = storePassword.toCharArray();
        
        if (filePath != null && filePath.length() > 0) {
            try {
                ks = KeyStore.getInstance(keyStoreType);
                try {
                    FileInputStream ksfis = new FileInputStream(filePath);                    
                    ks.load(ksfis, password);
                    ksfis.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("Creating new key store file: " + filePath);
                    ks.load(null, password);                    
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(filePath);
                    ks.store(fos, password);
                    fos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return ks;
    }

    public void createKeyEntry(String alias, String entryPassword) {
        try {
            if(getSecretKey(alias, entryPassword) != null) {
                return;
            }
            
            SecretKey sk = KeyGenerator.getInstance("DES").generateKey();
            SecretKeyEntry skEntry = new SecretKeyEntry(sk);
            ks.setEntry(alias, skEntry, new PasswordProtection(entryPassword.toCharArray()));
            java.io.FileOutputStream fos = new java.io.FileOutputStream(filePath);
            ks.store(fos, storePassword.toCharArray());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SecretKey getSecretKey(String alias, String entryPassword) {
        SecretKey sk = null;
        try {
            sk = (SecretKey) ks.getKey(alias, entryPassword.toCharArray());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sk;
    }
}
