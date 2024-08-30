package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.TypeEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BerryInputDto {
    @NotBlank
    @Size(min = 1, max = 10)
    @Column(unique = true)
    private String name;
    private String description;
    @Positive
    @Column(unique = true)
    private int indexNumber;
    @Positive
    private int growthTime;
    @NotNull
    @ValidEnum(enumClass = TypeEnum.BERRY_CATEGORY_TYPE.class, message = "Invalid Berry-Type Category")
    private TypeEnum.BERRY_CATEGORY_TYPE categoryType;


    private int spicyPotency;
    private int dryPotency;
    private int sweetPotency;
    private int bitterPotency;
    private int sourPotency;

}
