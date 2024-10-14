package com.novi.poffinhouse.dto.output.game;

import com.novi.poffinhouse.dto.output.LocationOutputDtoShort;
import lombok.Data;

import java.util.List;

@Data
public class GameMapOutputDto {
    private Long id;
    private Long gameId;
    private GameRegionMapOutputDto regionMap;
    private List<LocationOutputDtoShort> locationList;
}

