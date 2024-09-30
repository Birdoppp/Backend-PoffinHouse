package com.novi.poffinhouse.dto.output.game;

import com.novi.poffinhouse.util.PreferencesEnum;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GameOwnedPokemonOutputDto {
    private Long id;
    private String pokemonName;
    private String nickname;
    private PreferencesEnum.NATURE nature;

    public GameOwnedPokemonOutputDto(Long id,@Size(max = 12)String pokemonName, @Size(max = 12) String nickname, PreferencesEnum.NATURE nature) {
        this.id = id;
        this.pokemonName = pokemonName;
        this.nickname = nickname;
        this.nature = nature;
    }
}
