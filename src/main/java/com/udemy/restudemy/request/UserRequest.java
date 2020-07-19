package com.udemy.restudemy.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    //private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private List<AddressRequestModel> addresses;
}
