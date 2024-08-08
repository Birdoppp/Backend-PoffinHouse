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
    private Long id;
    @Setter
    @Column(unique = true, nullable = false)
    private String name;
    @Setter
    @Positive
    @Column(unique = true, nullable = false)
    private int nationalDex;
    @Setter
    @Column
    private TypeEnum.POKEMON_TYPE type;



//    @ManyToMany(mappedBy = "pokemons")
//    private List<Team> teams = new ArrayList<>();

}
