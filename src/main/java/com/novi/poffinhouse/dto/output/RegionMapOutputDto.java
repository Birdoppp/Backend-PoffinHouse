package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.models.region.RegionMapAtlas;
import lombok.Data;


@Data
public class RegionMapOutputDto {
    private Long id;
    private String regionName;
    private RegionMapAtlas regionMapAtlas;
    private int sizeXAxis;
    private int sizeYAxis;
    }
