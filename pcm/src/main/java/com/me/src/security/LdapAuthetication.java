/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.security;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author prakashj
 */
public class LdapAuthetication {
    public static void autheticateUser(String username, String password) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=ess");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid="+username+", ou=users, o=ess");
        env.put(Context.SECURITY_CREDENTIALS, password);
        
        DirContext ctx = new InitialDirContext(env);
        
        ctx.close();        
    }
}
