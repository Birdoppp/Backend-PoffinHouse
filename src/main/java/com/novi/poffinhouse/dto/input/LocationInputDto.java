package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class LocationInputDto {
    private String name;
    private String description;
    @Positive(message = "Value must be more than 0")
    private int coordinateX;
    @Positive(message = "Value must be more than 0")
    private int coordinateY;
    @NotNull
    private Long regionMapId;
    private List<BerryPlantingSiteOutputDto> berryPlantingSites;
}