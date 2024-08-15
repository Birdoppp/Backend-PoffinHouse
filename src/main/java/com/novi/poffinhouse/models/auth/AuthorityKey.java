package com.novi.poffinhouse.models.auth;

import lombok.Data;
import java.io.Serializable;

@Data
public class AuthorityKey implements Serializable {
    private Long id;
    private String authority;
    private String username;

    public AuthorityKey(Long id, String authority, String username) {
        this.id = id;
        this.authority = authority;
        this.username = username;
    }

    public AuthorityKey() {
    }
}