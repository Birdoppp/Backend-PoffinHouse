package com.novi.poffinhouse.models.pokemon;

import com.novi.poffinhouse.util.PreferencesEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OwnedPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    Pokemon species
    @Size(max = 12)
    private String nickname;
    private PreferencesEnum.NATURE nature;
    @Size(min = 1, max = 12)
    private String caughtByTrainerName;


    @ManyToOne
    private Pokemon pokemon;

}
