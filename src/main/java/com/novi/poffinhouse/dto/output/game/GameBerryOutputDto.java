package com.novi.poffinhouse.dto.output.game;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameBerryOutputDto {
    private Long id;
    private Long indexNumber;
    private String name;

    public GameBerryOutputDto(Long id, Long indexNumber, @NotBlank String name) {
        this.id = id;
        this.indexNumber = indexNumber;
        this.name = name;
    }
}
