package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class LocationInputDto {
    @NotBlank
    private String name;
    private String description;
    @PositiveOrZero
    private int coordinateX;
    @PositiveOrZero
    private int coordinateY;
    @PositiveOrZero
    private Long regionMapId;
    private List<BerryPlantingSiteOutputDto> berryPlantingSites;
}