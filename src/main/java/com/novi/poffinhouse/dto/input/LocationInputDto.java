package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.models.region.Location;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationInputDto extends Location {
    private String name;
    private String description;
    @Positive(message = "Value must be more than 0")
    private int coordinateX;
    @Positive(message = "Value must be more than 0")
    private int coordinateY;
}