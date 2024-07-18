package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.TypeEnum;
import lombok.Data;

@Data
public class BerryInputDto {
    private String name;
    private int indexNumber;

    private String description;
    private int growthTime;
    private TypeEnum.BerryCategoryType categoryType;

    private int spicyPotency;
    private int dryPotency;
    private int sweetPotency;
    private int bitterPotency;
    private int sourPotency;

}
