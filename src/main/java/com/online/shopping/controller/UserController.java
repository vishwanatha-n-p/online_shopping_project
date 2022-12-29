package com.online.shopping.controller;

import com.online.shopping.responsedto.UserResponseDto;
import com.online.shopping.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<UserResponseDto> getAllUser() {
        return userService.getAllUser();
    }

    @PreAuthorize("hasRole('ROLE_Customer')")
    @GetMapping("/{customerId}")
    public UserResponseDto getSingleUser(@PathVariable int userId) {
        return userService.getSingleUser(userId);
    }

    @PreAuthorize("hasRole('ROLE_Manager')")
    @DeleteMapping("/{userId}")
    public UserResponseDto removeUser(@PathVariable int userId) {
        return userService.removeUser(userId);
    }

}
