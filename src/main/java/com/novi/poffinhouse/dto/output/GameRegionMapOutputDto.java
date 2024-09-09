package com.novi.poffinhouse.dto.output;

import lombok.Data;

@Data
public class GameRegionMapOutputDto {
    private Long id;
    private String regionName;

    public GameRegionMapOutputDto(Long id, String regionName) {
        this.id = id;
        this.regionName = regionName;
    }
}
