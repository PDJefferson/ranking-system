package com.rankingsystem.backend.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class ProjectPasswordEncoderFactories {

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "bcrypt12";
        Map<String, PasswordEncoder> encoders = new HashMap();
        //override the default password encoder with the bcrypt one with a cost of 15
        encoders.put(encodingId, new BCryptPasswordEncoder(12));
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("argon2", new Argon2PasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    //don't instantiate class
    private ProjectPasswordEncoderFactories(){

    }
}
