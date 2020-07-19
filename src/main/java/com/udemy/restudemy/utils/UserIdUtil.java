package com.udemy.restudemy.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class UserIdUtil {

    private final Random RANDOM = new SecureRandom();

    private static final String ALPHABET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generatePublicUserId(int length)
    {
        return generateString(length);
    }

    private String generateString(int length)
    {
        StringBuilder string =new StringBuilder(length);

        for(int i=0;i<length;i++)
        {
            string.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(string);
    }

    public String generateAddressId(int length)
    {
        return generateString(length);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
