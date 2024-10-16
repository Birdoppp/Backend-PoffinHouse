package com.novi.poffinhouse.dto.output.game;

import com.novi.poffinhouse.models.region.Atlas;
import lombok.Data;

@Data
public class GameRegionMapOutputDto {
    private Long id;
    private String regionName;
    private Atlas atlas;

    public GameRegionMapOutputDto(Long id, String regionName, Atlas atlas) {
        this.id = id;
        this.regionName = regionName;
        this.atlas = atlas;
    }
}
