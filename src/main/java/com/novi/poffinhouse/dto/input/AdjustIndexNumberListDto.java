package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;
@Data
public class AdjustIndexNumberListDto {
    private List<Long> addIndexNumberList;
    private List<Long> removeIndexNumberList;
}
