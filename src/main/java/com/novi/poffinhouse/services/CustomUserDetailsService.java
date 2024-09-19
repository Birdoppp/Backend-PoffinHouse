package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.authenticate.AuthenticationRequest;
import com.novi.poffinhouse.models.auth.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthenticationRequest authenticationRequest = userService.logInUser(username);

        String password = authenticationRequest.getPassword();

         Set<Authority> authorities = authenticationRequest.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authenticationRequest.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority().toString()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}