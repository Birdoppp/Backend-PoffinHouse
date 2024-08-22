package com.novi.poffinhouse.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class LocationOutputDto {
    private Long id;
    private String name;
    private String description;
    private int coordinateX;
    private int coordinateY;
    private Long regionMapId;
    private List<BerryPlantingSiteOutputDto> berryPlantingSites;

}