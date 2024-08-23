package com.novi.poffinhouse.models.region;

import com.novi.poffinhouse.models.berries.Berry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Table(name = "berry_planting_sites")
public class BerryPlantingSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    @Column(nullable = false)
    private int soilSlots;

    @Setter
    @CollectionTable(name = "berry_planting_site_slots", joinColumns = @JoinColumn(name = "site_id"))
    @MapKeyColumn(name = "slot_number")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "berry_planting_site_berries",
            joinColumns = @JoinColumn(name = "site_id"),
            inverseJoinColumns = @JoinColumn(name = "berry_id"))
    private Map<Integer, Berry> plantedBerriesBySlots = new HashMap<>();

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public BerryPlantingSite(int soilSlots) {
        this.soilSlots = soilSlots;
        this.plantedBerriesBySlots = new HashMap<>(soilSlots);
    }

    protected BerryPlantingSite() {
    }

}
