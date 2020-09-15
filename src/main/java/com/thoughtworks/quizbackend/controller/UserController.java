package com.thoughtworks.quizbackend.controller;

import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@CrossOrigin
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public long createUser(@RequestBody @Valid User user) {
        return this.userService.createUser(user);
    }
}
