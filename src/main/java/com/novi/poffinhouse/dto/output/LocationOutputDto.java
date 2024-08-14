package com.novi.poffinhouse.dto.output;

import lombok.Data;

@Data
public class LocationOutputDto {
    private Long id;
    private String name;
    private String description;
    private int coordinateX;
    private int coordinateY;
    private String regionName;
}