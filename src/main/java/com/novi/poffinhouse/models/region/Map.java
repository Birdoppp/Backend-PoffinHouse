package com.novi.poffinhouse.models.region;

import com.novi.poffinhouse.models.region.Location;
import jakarta.persistence.*;
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
    private int sizeXAxis;
    @Setter
    @Column(nullable = false)
    private int sizeYAxis;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Location> map = new ArrayList<>();

    public void addLocation(Location location) {
        this.map.add(location);
    }

    public void removeLocation(Location location) {
        this.map.remove(location);
    }

    public void setLocations(List<Location> locations) {
        this.map = new ArrayList<>(locations);
    }

    public List<Location> getLocations() {
        return map;
    }
}
