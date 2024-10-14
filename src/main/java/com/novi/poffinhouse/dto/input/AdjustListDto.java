package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;
@Data
public class AdjustListDto {
    private List<Long> addList;
    private List<Long> removeList;
}
