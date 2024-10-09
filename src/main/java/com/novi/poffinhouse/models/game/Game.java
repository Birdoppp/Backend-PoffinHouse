package com.novi.poffinhouse.models.game;

import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.region.RegionMap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String versionName;
    @Setter
    @Column(nullable = false)
    private Integer generation;
    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_map_id", nullable = false)
    @Setter
    private RegionMap regionMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @Setter
    @JoinTable(
            name = "game_pokemon",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemonList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @Setter
    private List<OwnedPokemon> ownedPokemonList = new ArrayList<>();

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    @JoinColumn(name = "game_id")
    private Team team;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_berry",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "berry_id")
    )
    @Setter
    private List<Berry> berryList = new ArrayList<>();

}