package com.example.websecuritypasswordencoder.CustomizingPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class AspNetIdentityPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        try {
            return PasswordHasher.HashPasswordV3(charSequence.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        try {
            return PasswordHasher.VerifyHashedPassword(s, charSequence.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
