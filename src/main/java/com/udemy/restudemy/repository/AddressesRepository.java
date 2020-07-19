package com.udemy.restudemy.repository;

import com.udemy.restudemy.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressesRepository extends JpaRepository<Address,Long> {
}
