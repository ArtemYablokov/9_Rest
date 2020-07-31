package com.yabloko.controllers;


import com.yabloko.models.User;
import com.yabloko.services.UserService;
import com.yabloko.transfers.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // вместо просто контроллера
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        List<User> all = userService.findAll();

        List<UserDto> userDtos = all.stream()
                .map(user -> new UserDto(user.getName(), user.getLogin()))
                .collect(Collectors.toList());
        return userDtos;
    }

    @GetMapping("/users/{user-id}")
    public UserDto getUserById(@PathVariable("user-id") Long userId ){
        User user = userService.findById(userId);
        return UserDto.from(user);
    }

    @GetMapping("/")
    public String getString(){
        return "String";
    }


//    @ResponseBody // json - объект приходит с запросом
//    @DeleteMapping
//    @PostMapping
//    @PutMapping

}