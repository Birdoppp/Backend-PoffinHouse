package com.novi.poffinhouse.models.game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String description;

    @ManyToMany
    @Setter
    @JoinTable(
            name = "team_owned_pokemon",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "owned_pokemon_id")
    )
    private List<OwnedPokemon> ownedPokemon;

    @OneToOne (fetch = FetchType.LAZY)
    @Setter
    @JoinColumn(name = "game_id")
    private Game game;
}