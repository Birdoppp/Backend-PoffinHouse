package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.models.region.Atlas;
import lombok.Data;


@Data
public class RegionMapOutputDto {
    private Long id;
    private String regionName;
    private Atlas atlas;
    private int sizeXAxis;
    private int sizeYAxis;
    }
