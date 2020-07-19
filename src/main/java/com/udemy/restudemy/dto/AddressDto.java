package com.udemy.restudemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {

    private Long id;

    private String address_id;

    private String city;

    private String country;

    private String postal_code;

    private String street_name;

    private Long user_id;
}
