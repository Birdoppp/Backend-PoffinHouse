package com.novi.poffinhouse.models.region;

import com.novi.poffinhouse.models.berries.Berry;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class BerryPlantingSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String description;
    @Setter
    private int soilSlots;
    @Setter
    private HashMap<Integer, Berry> plantedBerriesBySlots;


    public BerryPlantingSite(int soilSlots) {
        this.soilSlots = soilSlots;
        this.plantedBerriesBySlots = new HashMap<>(soilSlots); // Initialize HashMap with initial capacity equal to soilSlots
    }

}
