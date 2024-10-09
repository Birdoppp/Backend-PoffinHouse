package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.TypeEnum;
import lombok.Data;


@Data
public class BerryOutputDto {
    private Long id;
    private String name;
    private Long indexNumber;

    private String description;
    private Integer growthTime;
    private TypeEnum.BERRY_CATEGORY_TYPE categoryType;

    private Integer spicyPotency;
    private Integer dryPotency;
    private Integer sweetPotency;
    private Integer bitterPotency;
    private Integer sourPotency;

    private Boolean validated;
}


