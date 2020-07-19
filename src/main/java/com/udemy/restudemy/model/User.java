package com.udemy.restudemy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID=4865903039190150223L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String user_id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 50)
    private String surname;

    @Column(nullable = false,length = 50,unique = true)
    private String email;

    @Column(nullable = false,length = 80)
    private String encryptedPassword;

    @Column(nullable = true,length = 50)
    private String emailVerificationToken;

    private Boolean emailVerificationStatus=false;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="users_details",
//            joinColumns = @JoinColumn( name="user_id"),
//            inverseJoinColumns = @JoinColumn( name="address_id")
//    )
    private List<Address> addresses=new ArrayList<>();

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setUser(this);
    }
}

