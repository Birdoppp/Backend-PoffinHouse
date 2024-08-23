package com.novi.poffinhouse.models.berries;

import com.novi.poffinhouse.util.TypeEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "berries")
public class Berry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    @Column(unique = true)
    private String name;

    @Setter
    @Positive
    @Column(unique = true)
    private int indexNumber;

    @Setter
    private String description;

    @Setter
    @Positive
    private int growthTime; //    In gen IV this ranges from 8 to 96 int in hours

    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = TypeEnum.BERRY_CATEGORY_TYPE.class, message = "Invalid Berry-Type Category")
    private TypeEnum.BERRY_CATEGORY_TYPE categoryType; // Marks the usage purpose for the berry


//    Potency per flavor
    @Setter
    private int spicyPotency;
    @Setter
    private int dryPotency;
    @Setter
    private int sweetPotency;
    @Setter
    private int bitterPotency;
    @Setter
    private int sourPotency;
}
