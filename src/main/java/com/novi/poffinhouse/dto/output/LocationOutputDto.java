package com.novi.poffinhouse.dto.output;

import lombok.Data;

@Data
public class LocationOutputDto {
    private int id;
    private String name;
    private int coordinateX;
    private int coordinateY;
}