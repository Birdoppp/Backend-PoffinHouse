package com.novi.poffinhouse.models.game;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String versionName;
    @Setter
    private int generation;
    @Setter
    private String description;

//    @PrePersist
//    @PreUpdate
//    private void validateTypeByGeneration(TypeEnum.POKEMON_TYPE type) {
//        if (generation < 6 && type == TypeEnum.POKEMON_TYPE.FAIRY) {
//            throw new IllegalArgumentException("Fairy type is not allowed for generation less than 6.");
//        }
//
//        if (generation < 2 && (type == TypeEnum.POKEMON_TYPE.DARK || type == TypeEnum.POKEMON_TYPE.GHOST)) {
//            throw new IllegalArgumentException("Dark or Ghost type is not allowed for generation less than 2.");
//        }
//    }
}
