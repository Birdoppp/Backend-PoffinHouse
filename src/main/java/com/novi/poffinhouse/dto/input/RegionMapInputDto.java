package com.novi.poffinhouse.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RegionMapInputDto {
    @NotBlank
    private String regionName;
    @Positive
    private int sizeXAxis;
    @Positive
    private int sizeYAxis;
}