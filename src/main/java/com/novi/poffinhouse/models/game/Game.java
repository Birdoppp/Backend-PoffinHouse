package com.novi.poffinhouse.models.game;

import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.pokemon.Team;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.util.TypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private int generation;
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
    @JoinTable(
            name = "game_pokemon",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemonList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @Setter
    private List<OwnedPokemon> ownedPokemonList = new ArrayList<>();

    @OneToOne(mappedBy = "game")
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

    public Game() {
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        for (Pokemon pokemon : pokemonList) {
            validateTypeByGeneration(pokemon.getType());
            validateNationalDexByGeneration(pokemon.getNationalDex());
        }
        this.pokemonList = pokemonList;
    }

    private void validateTypeByGeneration(TypeEnum.POKEMON_TYPE type) {
        if (generation < 6 && type == TypeEnum.POKEMON_TYPE.FAIRY) {
            throw new IllegalArgumentException("Fairy type is not allowed for generation less than 6.");
        }

        if (generation < 2 && (type == TypeEnum.POKEMON_TYPE.DARK || type == TypeEnum.POKEMON_TYPE.GHOST)) {
            throw new IllegalArgumentException("Dark or Ghost type is not allowed for generation less than 2.");
        }
    }

    private void validateNationalDexByGeneration(int nationalDex) {
        int maxDex = switch (generation) {
            case 1 -> 151;
            case 2 -> 251;
            case 3 -> 386;
            case 4 -> 493;
            case 5 -> 649;
            case 6 -> 721;
            case 7 -> 809;
            case 8 -> 905;
            case 9 -> 1025;
            default -> throw new IllegalArgumentException("Invalid generation.");
        };

        if (nationalDex > maxDex) {
            throw new IllegalArgumentException("Invalid national Dex number for Generation " + generation + ". Dex number must be less than or equal to " + maxDex +".");
        }
    }
}