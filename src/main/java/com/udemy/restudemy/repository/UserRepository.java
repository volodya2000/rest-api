package com.udemy.restudemy.repository;

import com.udemy.restudemy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query(nativeQuery = true,value = "select * from users where user_id=?1")
    Optional<User>findByUserId(String id);
    Optional<User> findByEmail(String email);
    @Override
    Page<User> findAll(Pageable pageable);
}
