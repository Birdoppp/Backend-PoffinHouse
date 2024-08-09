package com.novi.poffinhouse.models.pokemon;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

@Entity
@Getter
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(unique = true, nullable = false)
    private String name;
    @Setter
    @Positive
    @Column(unique = true)
    private int nationalDex;
    @Setter
    @Column
    private TypeEnum.POKEMON_TYPE type;

//    @Setter
//    @Positive
//    private Integer level;
//    @Setter
//    @Positive
//    private Integer healthPoints;
//    @Setter
//    @Positive
//    private Integer attack;
//    @Setter
//    @Positive
//    private Integer defence;
//    @Setter
//    @Positive
//    private Integer spAttack;
//    @Setter
//    @Positive
//    private Integer spDefence;
//    @Setter
//    @Positive
//    private Integer speed;


    @OneToMany
    private List<OwnedPokemon> ownedPokemonList;


//    @ManyToMany(mappedBy = "pokemons")
//    private List<Team> teams = new ArrayList<>();

}
