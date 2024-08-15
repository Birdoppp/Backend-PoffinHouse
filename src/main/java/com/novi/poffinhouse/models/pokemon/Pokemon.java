package com.novi.poffinhouse.models.pokemon;

import com.novi.poffinhouse.util.TypeEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;
    @Setter
    @Positive
    @Column(unique = true)
    private int nationalDex;
    @Setter
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = TypeEnum.POKEMON_TYPE.class, message = "Invalid Pokemon type.")
    private TypeEnum.POKEMON_TYPE type;

// Main Base Stats per Pokémon Species
    @Setter
    @Positive
    private Integer healthPoints;
    @Setter
    @Positive
    private Integer attack;
    @Setter
    @Positive
    private Integer defence;
    @Setter
    @Positive
    private Integer spAttack;
    @Setter
    @Positive
    private Integer spDefence;
    @Setter
    @Positive
    private Integer speed;


    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.REMOVE)
    private List<OwnedPokemon> ownedPokemonList;


//    @ManyToMany(mappedBy = "pokemon")
//    private List<Team> teams = new ArrayList<>();

}
