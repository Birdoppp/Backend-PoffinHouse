package com.novi.poffinhouse.models.pokemon;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

//import java.util.ArrayList;
//import java.util.List;

@Entity
@Getter
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Column(unique = true)
    private String name;
    @Setter
    @Positive
    @Column(unique = true)
    private int nationalDex;
    @Setter
    @Positive
    @Column(nullable = false)
    private int generation;
    @Setter
    @Column
    private TypeEnum.POKEMON_TYPE type;

    @PrePersist
    @PreUpdate
    private void validateTypeByGeneration() {
        if (generation < 6 && type == TypeEnum.POKEMON_TYPE.FAIRY) {
            throw new IllegalArgumentException("Fairy type is not allowed for generation less than 6.");
        }

        if (generation < 2 && (type == TypeEnum.POKEMON_TYPE.DARK || type == TypeEnum.POKEMON_TYPE.GHOST)) {
            throw new IllegalArgumentException("Dark or Ghost type is not allowed for generation less than 2.");
        }
    }

//    @ManyToMany(mappedBy = "pokemons")
//    private List<Team> teams = new ArrayList<>();

}
