package com.udemy.restudemy.controller;

import com.udemy.restudemy.dto.UserDto;
import com.udemy.restudemy.exceptions.UserServiceException;
import com.udemy.restudemy.request.UserRequest;
import com.udemy.restudemy.response.OperationStatusModel;
import com.udemy.restudemy.response.UserRest;
import com.udemy.restudemy.service.UserService;
import com.udemy.restudemy.utils.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping(value = "/create",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,},
           produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,})
    public UserRest createUser(@RequestBody UserRequest userDetails) throws UserServiceException
    {

        if(userDetails.getName().isEmpty()) throw  new UserServiceException("please,fill in name of user!");

        UserDto createdUser=userService.createUser(userMapper.toDtoFromRequest(userDetails));

        return  userMapper.toUserRest(createdUser);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getUsers(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                   @RequestParam(name = "limit",required = false,defaultValue = "10")int limit)
    {
        List<UserDto> users= userService.getAllUsers(page,limit);

       return users.stream().map(userDto -> userMapper.toUserRest(userDto)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public UserRest getUser(@PathVariable(value = "id")  String id) throws UserServiceException
    {
        UserDto userFromDB =userService.getUserByUserId(id);

        return userMapper.toUserRest(userFromDB);
    }

    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

    public UserRest updateUser( @PathVariable(value = "id") String id,@RequestBody UserRequest userRequest ) throws UserServiceException
    {

        UserDto userDtoRequest=new UserDto();

        BeanUtils.copyProperties(userRequest,userDtoRequest);

        UserDto updatedUser=userService.updateUser(id,userDtoRequest);

        UserRest returnValue=new UserRest();

        BeanUtils.copyProperties(updatedUser,returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel deleteUser( @PathVariable String id)
    {
        return userService.deleteUserByUserId(id);
    }

}
