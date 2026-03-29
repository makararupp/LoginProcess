package com.amk.bank.amk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amk.bank.amk.constant.LoginResponse;
import com.amk.bank.amk.dto.LoginDto;
import com.amk.bank.amk.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")

public class AutController {
	@Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
