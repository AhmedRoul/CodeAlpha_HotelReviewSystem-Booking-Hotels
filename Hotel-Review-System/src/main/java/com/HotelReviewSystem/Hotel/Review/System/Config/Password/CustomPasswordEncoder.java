package com.HotelReviewSystem.Hotel.Review.System.Config.Password;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.spec.InvalidKeySpecException;

@Slf4j
@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    public CustomPasswordEncoder() {
    }

    @SneakyThrows
    public String encode(CharSequence charSequence) {
        return SecurityConfigurationPassword.NewPasswordEncoder(charSequence.toString());
    }

    @SneakyThrows
    public boolean matches(CharSequence charSequence, String s) {

        return SecurityConfigurationPassword.matchPasswords(charSequence.toString(), s);
    }
    }
