package com.novi.poffinhouse.dto.input;

import lombok.Data;

@Data
public class LocationInputDto {
    private String name;
    private int coordinateX;
    private int coordinateY;
}