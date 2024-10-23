package com.novi.poffinhouse.models.game.gamemap;

import com.novi.poffinhouse.models.berries.Berry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "berry_planting_sites")
public class BerryPlantingSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Setter
    @Column(nullable = false)
    private Integer soilSlots;

    @Setter
    @CollectionTable(name = "berry_planting_site_slots", joinColumns = @JoinColumn(name = "site_id"))
    @MapKeyColumn(name = "slot_number")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "berry_planting_site_berries",
            joinColumns = @JoinColumn(name = "site_id"),
            inverseJoinColumns = @JoinColumn(name = "berry_id"))
    private Map<Integer, Berry> plantedBerriesBySlots = new HashMap<>();


    public BerryPlantingSite(int soilSlots) {
        this.soilSlots = soilSlots;
        this.plantedBerriesBySlots = new HashMap<>(soilSlots);
    }

}
