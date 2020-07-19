package com.udemy.restudemy.service;

import com.udemy.restudemy.dto.UserDto;
import com.udemy.restudemy.exceptions.UserServiceException;
import com.udemy.restudemy.model.User;
import com.udemy.restudemy.repository.UserRepository;
import com.udemy.restudemy.response.OperationStatusModel;
import com.udemy.restudemy.response.RequestOperationName;
import com.udemy.restudemy.response.RequestOperationStatus;
import com.udemy.restudemy.utils.AddressMapper;
import com.udemy.restudemy.utils.UserIdUtil;
import com.udemy.restudemy.utils.UserMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Getter
@Setter
public class UserService implements UserDetailsService {

    private UserIdUtil userIdUtil;
    private UserRepository userRepository;
    private AddressMapper addressMapper;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,UserIdUtil userIdUtil,AddressMapper addressMapper,UserMapper userMapper) {
        this.userIdUtil=userIdUtil;
        this.userRepository = userRepository;
        this.addressMapper=addressMapper;
        this.userMapper=userMapper;
    }

    public UserDto createUser(UserDto userDto) throws UserServiceException
    {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) throw new  UserServiceException("User with given email is exist");

        userDto.getAddresses().forEach(address -> {
            address.setAddress_id(userIdUtil.generateAddressId(15));
        });

        User user=userMapper.toEntity(userDto);

        user.setUser_id(userIdUtil.generatePublicUserId(15));

        user.setEncryptedPassword(userIdUtil.bCryptPasswordEncoder().encode(userDto.getPassword()));

        User savedUser=userRepository.save(user);

        return userMapper.toDto(savedUser);

    }

    public UserDto updateUser(String id,UserDto userDto) throws UserServiceException
    {

        Optional<User> user=userRepository.findByUserId(id);

        if(!user.isPresent()) throw new UserServiceException("updated user does not exist");

        user.get().setName(userDto.getName());
        user.get().setSurname(userDto.getSurname());

        return userMapper.toDto(userRepository.save(user.get()));

    }

    public UserDto getUserByEmail(String email) throws UsernameNotFoundException
    {
        Optional<User> userFromDB= userRepository.findByEmail(email);

        if(!userFromDB.isPresent()) throw new UserServiceException("user does not exist");

        return userMapper.toDto(userFromDB.get());
    }


    public UserDto getUserByUserId(String id) throws UserServiceException
    {
        Optional<User> userFromDB=userRepository.findByUserId(id);
        if(!userFromDB.isPresent()) throw new UserServiceException("user does not exist");

        return userMapper.toDto(userFromDB.get());
    }


    public OperationStatusModel deleteUserByUserId(String id)
    {

        Optional<User> user=userRepository.findByUserId(id);

        if(!user.isPresent()) throw new UserServiceException("deleted user does not exist");

        userRepository.delete(user.get());

        return new OperationStatusModel().setOperationName(RequestOperationName
        .DELETE.name()).setOperationResult(RequestOperationStatus.SUCCESS.name());
    }

    public List<UserDto> getAllUsers(int page,int limit) throws UserServiceException
    {
        PageRequest pageRequest=PageRequest.of(page,limit);

        List<User> users= userRepository.findAll(pageRequest).getContent();

        if(users.isEmpty())throw new UserServiceException("there are no users in the database");

        return users.stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new UsernameNotFoundException(email));

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getEncryptedPassword(),new ArrayList<>());
    }
}
