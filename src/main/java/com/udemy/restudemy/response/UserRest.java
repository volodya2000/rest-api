package com.udemy.restudemy.response;

import com.udemy.restudemy.request.AddressRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRest {

    private String user_id;

    private String name;

    private String surname;

    private String email;

    private List<AddressRest> addressRest;
}
