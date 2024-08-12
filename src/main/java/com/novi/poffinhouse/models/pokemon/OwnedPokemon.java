package com.novi.poffinhouse.models.pokemon;

import com.novi.poffinhouse.util.PreferencesEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class OwnedPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    Pokemon species
    @ManyToOne
    @Setter
    @JoinColumn(nullable = false)
    @NotNull
    @Valid
    private Pokemon pokemon;

    @Setter
    @Size(max = 12)
    private String nickname;
    @Setter
    @ValidEnum(enumClass = PreferencesEnum.NATURE.class)
    private String nature;
    @Setter
    @Size(min = 1, max = 12)
    private String caughtByTrainerName;

    // Contest conditions
    @Setter
    private Integer beauty;
    @Setter
    private Integer coolness;
    @Setter
    private Integer cuteness;
    @Setter
    private Integer cleverness;
    @Setter
    private Integer toughness;

}
