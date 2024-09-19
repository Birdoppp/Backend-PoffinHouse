package com.novi.poffinhouse.dto.output;

import lombok.Data;

@Data
public class GameUserOutputDto {
    private Long id;
    private String username;

    public GameUserOutputDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
