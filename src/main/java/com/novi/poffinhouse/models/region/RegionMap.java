package com.novi.poffinhouse.models.region;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "region_maps")
public class RegionMap {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Column(name = "region_name", unique = true)
    private String regionName;

    @Setter
    @Column(name = "horizontal_axis", nullable = false)
    @Positive
    private int sizeXAxis;

    @Setter
    @Column(name = "vertical_axis", nullable = false)
    @Positive
    private int sizeYAxis;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "regionMap")
    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location location) {
        location.setRegionMap(this); // Ensure bidirectional relationship
        this.locations.add(location);
    }

    public void removeLocation(Location location) {
        this.locations.remove(location);
        location.setRegionMap(null); // Break the bidirectional relationship
    }

}
