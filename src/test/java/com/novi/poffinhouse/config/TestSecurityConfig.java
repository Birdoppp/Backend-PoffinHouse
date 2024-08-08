//package com.novi.poffinhouse.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.test.context.support.WithSecurityContextFactory;
//import org.springframework.test.context.ActiveProfiles;
//
//@Configuration
//@EnableWebSecurity
//@ActiveProfiles("test")
//public class TestSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()  // Disable CSRF for tests
//                .authorizeRequests()
//                .anyRequest().permitAll();  // Allow all requests
//    }
//}
