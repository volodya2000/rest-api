package com.udemy.restudemy.utils;

import com.udemy.restudemy.dto.UserDto;
import com.udemy.restudemy.model.Address;
import com.udemy.restudemy.model.User;
import com.udemy.restudemy.request.UserRequest;
import com.udemy.restudemy.response.UserRest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Component
public class UserMapper {

    private ModelMapper modelMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    public UserMapper(ModelMapper modelMapper)
    {
        this.modelMapper=modelMapper;
    }

    public User toEntity(UserDto userDto)
    {
        List<Address> addresses= userDto.getAddresses().stream().map(addressDto ->
            addressMapper.toEntity(addressDto)).collect(Collectors.toList());
        User user=modelMapper.map(userDto,User.class);
        addresses.forEach(address -> {address.setUser(user);});
        user.setAddresses(addresses);
        return user;
    }

    public UserDto toDto(User user)
    {
        return Objects.isNull(user)?null:modelMapper.map(user,UserDto.class);
    }


    public UserRest toUserRest(UserDto userDto)
    {
        UserRest userRest=Objects.isNull(userDto)?null:modelMapper.map(userDto,UserRest.class);
        userRest.setAddressRest(userDto.getAddresses().stream().map(addressDto -> addressMapper.toAddressRest(addressDto)).collect(Collectors.toList()));
        return userRest;
    }

    public UserDto toDtoFromRequest(UserRequest userRequest)
    {return Objects.isNull(userRequest)?null:modelMapper.map(userRequest,UserDto.class);}

}
