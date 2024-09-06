package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.PreferencesEnum;
import jakarta.validation.constraints.Size;

public class GameOwnedPokemonOutputDto {
    private Long id;
    private String nickname;
    private PreferencesEnum.NATURE nature;

    public GameOwnedPokemonOutputDto(Long id, @Size(max = 12) String nickname, PreferencesEnum.NATURE nature) {
    }
}
