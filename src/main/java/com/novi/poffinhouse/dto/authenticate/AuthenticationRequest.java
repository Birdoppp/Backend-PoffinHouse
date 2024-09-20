package com.novi.poffinhouse.dto.authenticate;

import com.novi.poffinhouse.models.auth.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class AuthenticationRequest {
    private String username;
    private String email;
    private String password;
    private Set<Authority> authorities;
}
