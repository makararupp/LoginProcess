package com.amk.bank.amk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    //1. Define Security Fitter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(csrf -> csrf.disable());
        // Config Http Mapping URL
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/hello").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
        );
        return http.build();
    }

    //2. Define Bean : AuthenticationManager [User InMemory]
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("dara")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("makara")
                .password(passwordEncoder.encode("112233"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
