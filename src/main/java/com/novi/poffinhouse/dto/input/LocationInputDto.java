package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LocationInputDto {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private int coordinateX;
    @NotNull
    private int coordinateY;
    @NotNull
    private Long regionMapId;
    private List<BerryPlantingSiteOutputDto> berryPlantingSites;
}