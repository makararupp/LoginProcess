package com.amk.bank.amk.controller;

import com.amk.bank.amk.dto.request.LoginRequest;
import com.amk.bank.amk.dto.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        try {
            //Load user from  spring security
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUser());
            // check password
            if(passwordEncoder.matches(request.getPassword(), userDetails.getPassword())){
                log.info("Login Success for:"+ request.getUser());

                return new LoginResponse("SUCCESS", "Login successful.");
            }else {
                log.info("Login failed: wrong password");
                return new LoginResponse("FAIL","Invalid username or password");
            }

        } catch (UsernameNotFoundException ex) {
            log.info("Login failed: user not found");
            return new LoginResponse("FAIL","Invalid username or password");
        }

    }

}
