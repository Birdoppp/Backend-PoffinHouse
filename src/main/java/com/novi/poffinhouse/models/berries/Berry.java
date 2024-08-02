package com.novi.poffinhouse.models.berries;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Berry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    @Setter
    private String name;
    @Column (unique = true)
    @Setter
    private int indexNumber;

    @Setter
    private String description;
    @Setter
    private int growthTime; //    In genIV this ranges from 8 to 96 int in hours
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
