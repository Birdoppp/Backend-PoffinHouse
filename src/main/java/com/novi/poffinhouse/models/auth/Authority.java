package com.novi.poffinhouse.models.auth;

import com.novi.poffinhouse.util.RoleEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = RoleEnum.class, message = "Invalid role")
    private RoleEnum authority;

    @Id
    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private User user;

}