package com.udemy.restudemy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.restudemy.context.SpringApplicationContext;
import com.udemy.restudemy.dto.UserDto;
import com.udemy.restudemy.request.UserLoginRequestModel;
import com.udemy.restudemy.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Getter

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UserLoginRequestModel user=new ObjectMapper()
                    .readValue(request.getInputStream(),UserLoginRequestModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword(),new ArrayList<>()));
        }catch (IOException ex)
        {
            throw new RuntimeException("could not read input login information");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String email=((User)authResult.getPrincipal()).getUsername();

        String token= Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.getTokenSecret())
                .compact();

        UserService userDetails= (UserService) SpringApplicationContext.getBean("userService");

        UserDto user= userDetails.getUserByEmail(email);

        response.addHeader("user_id",user.getUser_id());

        response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+token);
    }
}
