package com.based.genzedong.BasedReddit.controllers;

import com.based.genzedong.BasedReddit.entity.User;
import com.based.genzedong.BasedReddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {

        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
