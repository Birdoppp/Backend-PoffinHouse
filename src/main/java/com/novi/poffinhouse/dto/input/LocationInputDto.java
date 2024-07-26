package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.models.region.Map;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class LocationInputDto {
    private String name;
    private String description;
    @Positive(message = "Value must be more than 0")
    private int coordinateX;
    @Positive(message = "Value must be more than 0")
    private int coordinateY;
    private Map map;
}