package com.mmarques.security;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;


public class AuthenticationService {
        public boolean authenticate(String authCredentials){
            if(null == authCredentials) return false;
            //O formato do header sera "Basic encondedstring" para Basic authentication
            final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ","");
            System.out.println("encodedUserPassord: " + encodedUserPassword);
            
            String usernameAndPassword = null;
            try{
                byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
                usernameAndPassword = new String(decodedBytes,"UTF-8");
            }catch(IOException e){
                e.printStackTrace();
            }
            
            //A classe StringTokenizer quebra uma String em tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword,":");
            
            if(tokenizer.countTokens()<2) return false;
            
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            
            boolean autenticationStatus = "marcos".equals(username) && "123".equals(password);
            return autenticationStatus;
        }
}
