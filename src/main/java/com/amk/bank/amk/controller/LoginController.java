package com.amk.bank.amk.controller;

import com.amk.bank.amk.dto.request.LoginRequest;
import com.amk.bank.amk.dto.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        try {
            log.info("Login attempt for user: {}", request.getUser());

            UserDetails userDetails = userDetailsService
                    .loadUserByUsername(request.getUser());

            if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                log.info("Login success for user: {}", request.getUser());
                return new LoginResponse("SUCCESS", "Login successful.");
            } else {
                log.warn("Login failed: wrong password for user: {}", request.getUser());
                return new LoginResponse("FAIL", "Invalid username or password");
            }

        } catch (UsernameNotFoundException ex) {
            log.warn("Login failed: user not found: {}", request.getUser());
            return new LoginResponse("FAIL", "Invalid username or password");
        }
    }

}
