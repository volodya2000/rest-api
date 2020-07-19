package com.udemy.restudemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID=4865903039190150223L;

    private Long id;

    private String user_id;

    private String name;

    private String surname;

    private String password;

    private String email;

    private String encryptedPassword;

    private String emailVerificationToken;

    private Boolean emailVerificationStatus=false;

    private List<AddressDto> addresses=new ArrayList<>();
}
