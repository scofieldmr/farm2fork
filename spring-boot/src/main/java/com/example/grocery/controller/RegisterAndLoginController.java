package com.example.grocery.controller;

import com.example.grocery.dto.LoginDto;
import com.example.grocery.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/grocery")
@RestController
public class RegisterAndLoginController {

    @Autowired
    CredentialsService credentialsService;
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDto loginUser) {
        //Logging in functionality
        System.out.println(loginUser);
        return credentialsService.authenticate(loginUser.getEmail(), loginUser.getPassword());
    }

}
