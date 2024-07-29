package com.novi.poffinhouse.models.region;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Column(unique = true)
    private String regionName;
    @Setter
    @Column(nullable = false)
    @Positive
    private int sizeXAxis;
    @Setter
    @Column(nullable = false)
    @Positive
    private int sizeYAxis;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "map")
    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public void removeLocation(Location location) {
        this.locations.remove(location);
    }

}
