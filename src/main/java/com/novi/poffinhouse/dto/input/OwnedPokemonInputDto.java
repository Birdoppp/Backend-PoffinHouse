package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.PreferencesEnum;
import lombok.Data;

@Data
public class OwnedPokemonInputDto {
    private String pokemonName;
    private String nickname;
    private PreferencesEnum.NATURE nature;
    private String caughtByTrainerName;

}
