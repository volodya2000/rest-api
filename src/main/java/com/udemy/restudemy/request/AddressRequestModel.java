package com.udemy.restudemy.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressRequestModel {

    private String city;

    private String country;

    private String street_name;

    private String postal_code;
}
