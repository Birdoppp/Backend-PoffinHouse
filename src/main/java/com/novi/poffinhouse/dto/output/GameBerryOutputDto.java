package com.novi.poffinhouse.dto.output;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameBerryOutputDto {
    private Long id;
    private String name;

    public GameBerryOutputDto(Long id, @NotBlank String name) {
    }
}
