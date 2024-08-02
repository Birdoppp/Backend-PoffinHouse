package com.novi.poffinhouse.models.game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String versionName;
    @Setter
    private int pokemonGeneration;
    @Setter
    private String description;
}
