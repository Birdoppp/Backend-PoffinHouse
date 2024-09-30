package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class GameIdListSetterInputDto {
    private List<Long> idList;
    private boolean autofill;
}
