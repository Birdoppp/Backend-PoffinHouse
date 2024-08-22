package com.novi.poffinhouse.dto.output;

import lombok.Data;

import java.util.Map;
@Data
public class BerryPlantingSiteOutputDto {
    private Long id;
    private String description;
    private int soilSlots;
    private Map<Integer, Long> plantedBerriesBySlots;
    private Long locationId;
}
