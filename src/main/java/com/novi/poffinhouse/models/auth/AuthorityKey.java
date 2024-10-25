package com.novi.poffinhouse.models.auth;

import com.novi.poffinhouse.util.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@Data
public class AuthorityKey implements Serializable {

    private RoleEnum authority;
    private String username;

    public AuthorityKey( RoleEnum authority, String username) {
        this.authority = authority;
        this.username = username;
    }
}