package com.udemy.restudemy.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequestModel {

    private String email;

    private String password;

}
