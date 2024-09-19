package com.novi.poffinhouse.models.auth;

import com.novi.poffinhouse.models.game.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Email(message = "Email is not valid")
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 50, message = "Name must be between 4 and 100 characters")
    private String username;

    @Setter
    @NotBlank
    @Column(nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;


    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Game> games = new HashSet<>();

    public User() {
    }
}
