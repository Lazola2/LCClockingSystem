package com.lcclockingsystem.sbcrud.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // use for registration
    public String hashPassword(String password){
        return passwordEncoder.encode(password);
    }

    // use when logging in
    public boolean isPasswordValid(String password, String hashedPassword){
        return passwordEncoder.matches(password, hashedPassword);
    }
}
