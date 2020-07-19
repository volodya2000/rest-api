package com.udemy.restudemy.response;

import com.udemy.restudemy.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressRest {

    private String address_id;

    private String country;

    private String city;

    private String postal_code;

    private String street_name;

    private String user_id;
}
