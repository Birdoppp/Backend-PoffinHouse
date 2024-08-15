package com.novi.poffinhouse.models.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Id
    @Column(nullable = false)
    @Setter
    private String authority;

    @Id
    @Column(nullable = false)
    @Setter
    private String username;

    @Setter
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private User user;

    public Authority() {}

    public Authority(Long id, String authority, String username) {
        this.id = id;
        this.authority = authority;
        this.username = username;
    }

}