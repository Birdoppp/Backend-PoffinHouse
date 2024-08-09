package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.PreferencesEnum;
import lombok.Data;

@Data
public class OwnedPokemonOutputDto {
    private Long id;
    private String pokemonName;
    private String nickname;
    private PreferencesEnum.NATURE nature;
    private String caughtByTrainerName;
}
