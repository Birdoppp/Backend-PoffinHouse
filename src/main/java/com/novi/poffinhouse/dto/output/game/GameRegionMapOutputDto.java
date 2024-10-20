package com.novi.poffinhouse.dto.output.game;

import com.novi.poffinhouse.models.region.RegionMapAtlas;
import lombok.Data;

@Data
public class GameRegionMapOutputDto {
    private Long id;
    private String regionName;
    private RegionMapAtlas regionMapAtlas;

    public GameRegionMapOutputDto(Long id, String regionName, RegionMapAtlas regionMapAtlas) {
        this.id = id;
        this.regionName = regionName;
        this.regionMapAtlas = regionMapAtlas;
    }
}
