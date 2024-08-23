package com.novi.poffinhouse.dto.output;

import lombok.Data;

@Data
public class LocationOutputDtoShort {
    private Long id;
    private String name;
    private int coordinateX;
    private int coordinateY;
    private int totalBerryPlantingSites;
    private int totalPlantedBerries;
}
