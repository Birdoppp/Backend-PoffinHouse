package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.TypeEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
    private Long indexNumber;
    @Positive
    private Integer growthTime;
    @NotNull
    @ValidEnum(enumClass = TypeEnum.BERRY_CATEGORY_TYPE.class, message = "Invalid Berry-Type Category")
    private TypeEnum.BERRY_CATEGORY_TYPE categoryType;

    @PositiveOrZero
    private Integer spicyPotency;
    @PositiveOrZero
    private Integer dryPotency;
    @PositiveOrZero
    private Integer sweetPotency;
    @PositiveOrZero
    private Integer bitterPotency;
    @PositiveOrZero
    private Integer sourPotency;

}
