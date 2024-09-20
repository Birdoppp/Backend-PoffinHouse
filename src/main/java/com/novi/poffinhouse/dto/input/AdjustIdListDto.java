package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;
@Data
public class AdjustIdListDto {
    private List<Long> addIdList;
    private List<Long> removeIdList;
}
