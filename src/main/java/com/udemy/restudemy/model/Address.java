package com.udemy.restudemy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address implements Serializable {

    private static final long serialVersionUID=7809200551672852690L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String address_id;

    @Column(nullable = false,length = 50)
    private String country;

    @Column(nullable = false,length = 50)

    private String city;
    @Column(nullable = false,length = 50)
    private String postal_code;

    @Column(nullable = false,length = 50)
    private String street_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
