package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.PreferencesEnum;
import lombok.Data;

@Data
public class PreferencesDto {
    private String natureName;
    private PreferencesEnum.FLAVOR favoriteFlavor;
    private PreferencesEnum.FLAVOR dislikedFlavor;

    public PreferencesDto(String natureName, PreferencesEnum.FLAVOR favoriteFlavor, PreferencesEnum.FLAVOR dislikedFlavor) {
        this.natureName = natureName;
        this.favoriteFlavor = favoriteFlavor;
        this.dislikedFlavor = dislikedFlavor;
    }
}
