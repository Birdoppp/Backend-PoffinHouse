package com.novi.poffinhouse.dto.output;

import lombok.Data;


@Data
public class RegionMapOutputDto {
    private Long id;
    private String regionName;
    private int sizeXAxis;
    private int sizeYAxis;
    }
