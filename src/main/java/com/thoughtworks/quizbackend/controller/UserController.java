package com.thoughtworks.quizbackend.controller;

import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return this.userService.getUserById(id);
    }
}
