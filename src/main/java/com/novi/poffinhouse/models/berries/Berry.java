package com.novi.poffinhouse.models.berries;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Berry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    @Column(unique = true)
    @NotBlank
    private String name;
    @Setter
    @Column(unique = true)
    @Positive
    private int indexNumber;

    @Setter
    private String description;
    @Setter
    @Positive
    private int growthTime; //    In gen IV this ranges from 8 to 96 int in hours
    @Setter
    private TypeEnum.BERRY_TYPE_CATEGORY typeCategory;

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
