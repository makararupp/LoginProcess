package com.amk.bank.amk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable().authorizeHttpRequests(auth -> auth.anyRequest().permitAll()));
		return http.build();

	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
		UserDetails user = User.builder().username("makara").password(encoder.encode("12345678")).roles("USER").build();
		UserDetails admin = User.builder().username("dara").password(encoder.encode("7897899")).roles("ADMIN", "USER")
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}
}
