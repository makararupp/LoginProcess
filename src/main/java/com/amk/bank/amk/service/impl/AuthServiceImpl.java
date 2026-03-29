package com.amk.bank.amk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.amk.bank.amk.constant.LoginResponse;
import com.amk.bank.amk.dto.LoginDto;
import com.amk.bank.amk.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
	private final PasswordEncoder passwordEncoder;

	@Override
	public LoginResponse login(LoginDto loginDto) {
		log.info("Login request:{}", loginDto.getUser());
		try {
			UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(loginDto.getUser());
			LoginResponse response;
			if (passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
				response = LoginResponse.builder().status(LoginResponse.SUCCESS).message("Login Sucessfully.").build();
				log.info("Login response:{}", response);
				return response;
			} else {
				response = LoginResponse.builder().status(LoginResponse.FAIL).message("Invalid User or Password.")
						.build();
				log.info("Login response:{}", response);
				return response;
			}

		} catch (Exception e) {
			LoginResponse response = LoginResponse.builder().status(LoginResponse.FAIL).message("User not found")
					.build();
			log.info("Login response:{}", response);
			return response;
		}

	}
}
