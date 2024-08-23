package com.novi.poffinhouse.models.region;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;
    @Setter
    private String description;
    @Setter
    @PositiveOrZero
    @Column(nullable = false, name = "coordinate_X")
    private int coordinateX;
    @Setter
    @PositiveOrZero
    @Column(nullable = false, name = "coordinate_Y")
    private int coordinateY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_map_id", nullable = false)
    @Setter
    private RegionMap regionMap;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    private List<BerryPlantingSite> berryPlantingSites = new ArrayList<>();

    public void addBerryPlantingSite(BerryPlantingSite site) {
        site.setLocation(this); // Ensure bidirectional relationship
        this.berryPlantingSites.add(site);
    }

    public void removeBerryPlantingSite(BerryPlantingSite site) {
        this.berryPlantingSites.remove(site);
        site.setLocation(null); // Break the bidirectional relationship
    }


}
