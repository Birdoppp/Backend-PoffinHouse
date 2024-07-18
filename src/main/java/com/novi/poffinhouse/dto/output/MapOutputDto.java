package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.models.region.Location;
import lombok.Data;

import java.util.List;

@Data
public class MapOutputDto {
    private int id;
    private String regionName;
    private int sizeXAxis;
    private int sizeYAxis;
    private List<Location> locations;
}