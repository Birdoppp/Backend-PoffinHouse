package com.novi.poffinhouse.models.game;

import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.util.PreferencesEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Table(name = "owned_pokemon")
public class OwnedPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Setter
    @JoinColumn(name = "game_id")
    private Game game;

    @Setter
    @NotNull
    private String username;

    @ManyToOne
    @Setter
    @JoinColumn(nullable = false)
    @NotNull
    @Valid
    private Pokemon pokemon; //    Pokemon species

    @Setter
    @Size(max = 12)
    private String nickname;
    @Setter
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = PreferencesEnum.NATURE.class, message = "Invalid nature. Valid values are: SASSY, BOLD, etc.")
    private PreferencesEnum.NATURE nature;
    @Setter
    @Size(min = 1, max = 12)
    private String caughtByTrainerName;


    // Contest conditions for the Pokémon Contests
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


    @ManyToMany(mappedBy = "ownedPokemon")
    private List<Team> teams;

}
