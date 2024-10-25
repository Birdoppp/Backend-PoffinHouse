package com.novi.poffinhouse.models.berries;

import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.util.Capitalize;
import com.novi.poffinhouse.util.TypeEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Table(name = "berries")
public class Berry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Setter
    @Positive
    @Column(unique = true)
    private Long indexNumber;

    @Setter
    private String description;

    @Setter
    @Positive
    private Integer growthTime; //    In gen IV this ranges from 8 to 96 int in hours

    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = TypeEnum.BERRY_CATEGORY_TYPE.class, message = "Invalid Berry-Type Category")
    private TypeEnum.BERRY_CATEGORY_TYPE categoryType; // Marks the usage purpose for the berry

    //    Potency per flavor
    @Setter
    @PositiveOrZero
    private Integer spicyPotency;
    @Setter
    @PositiveOrZero
    private Integer dryPotency;
    @Setter
    @PositiveOrZero
    private Integer sweetPotency;
    @Setter
    @PositiveOrZero
    private Integer bitterPotency;
    @Setter
    @PositiveOrZero
    private Integer sourPotency;

    @Setter
    private Boolean validated;

    @Setter
    @ManyToMany(mappedBy = "berryList")
    private List<Game> games;

    public void setName(String name) {
        this.name = name != null ? Capitalize.getCapitalizedString(name) : null;
    }
}
