package com.example.websecuritypasswordencoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class AspNetIdentityPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.equals(s);
    }
}
