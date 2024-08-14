package com.novi.poffinhouse.models.pokemon;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//import java.util.ArrayList;
//import java.util.List;
@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String description;

//    @ManyToMany
//    @JoinTable(
//            name = "team_pokemon",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
//    )
//    @Getter
//    @Setter
//    private List<Pokemon> pokemon = new ArrayList<>();
//
//
//    public static final int MAX_POKEMON = 6;
//
//    public void addPokemon(Pokemon pokemon) {
//        if (pokemon.size() < MAX_POKEMON) {
//            pokemon.add(pokemon);
//            pokemon.getTeams().add(this);
//        } else {
//            throw new IllegalStateException("A team can only have up to 6 Pokemon.");
//        }
//    }
//    public void removePokemon(Pokemon pokemon) {
//        pokemon.remove(pokemon);
//        pokemon.getTeams().remove(this);
//    }

}
