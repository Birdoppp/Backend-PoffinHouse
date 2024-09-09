package com.novi.poffinhouse.dto.output;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameBerryOutputDto {
    private Long id;
    private int indexNumber;
    private String name;

    public GameBerryOutputDto(Long id, int indexNumber, @NotBlank String name) {
        this.id = id;
        this.indexNumber = indexNumber;
        this.name = name;
    }
}
