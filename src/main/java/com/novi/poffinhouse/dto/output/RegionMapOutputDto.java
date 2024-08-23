package com.novi.poffinhouse.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class RegionMapOutputDto {
    private Long id;
    private String regionName;
    private int sizeXAxis;
    private int sizeYAxis;
    private List<LocationOutputDtoShort> locations;
}