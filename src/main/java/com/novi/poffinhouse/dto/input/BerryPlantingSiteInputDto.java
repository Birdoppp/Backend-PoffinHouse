package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class BerryPlantingSiteInputDto {
    private String description;
    private int soilSlots;
    private Map<Integer, Long> plantedBerriesBySlots = new HashMap<>();
    private Long locationId;
}
