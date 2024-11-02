package com.novi.poffinhouse.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class LocationInputDto {
    @NotBlank
    private String name;
    private String description;
    @PositiveOrZero
    private Long gameMapId;
    @PositiveOrZero
    private Integer coordinateX;
    @PositiveOrZero
    private Integer coordinateY;
}