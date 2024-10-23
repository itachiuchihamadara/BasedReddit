package com.based.genzedong.BasedReddit.controllers;

import com.based.genzedong.BasedReddit.entity.User;
import com.based.genzedong.BasedReddit.entity.UserProfileConfig;
import com.based.genzedong.BasedReddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<?> addNewUser(@RequestBody User user) throws IOException {

        this.userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getProfileConfig")
    public ResponseEntity<?> getProfileConfig(@RequestParam Long userID) throws IOException {

        UserProfileConfig userProfileConfig = this.userService.getProfileConfig(userID);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();

        body.add("userId", userProfileConfig.getUserId());
        body.add("userBio", userProfileConfig.getUserBio());
        body.add("userLocation", userProfileConfig.getUserLocation());
        body.add("dateOfBirth", userProfileConfig.getDateOfBirth());
        body.add("profileImage", userProfileConfig.getProfileImage().getBytes());
        body.add("bannerImage", userProfileConfig.getBannerImage().getBytes());

        return new ResponseEntity<>(userProfileConfig, headers, HttpStatus.OK);

    }

    @GetMapping(value = "/getProfileImage")
    public ResponseEntity<byte[]> getProfileImage(@RequestParam Long userID) throws IOException {

        UserProfileConfig userProfileConfig = this.userService.getProfileConfig(userID);
        return new ResponseEntity<>(userProfileConfig.getProfileImage().getBytes(), HttpStatus.OK);

    }


}
