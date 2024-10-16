package com.novi.poffinhouse.models.region;

import com.novi.poffinhouse.models.game.GameMap;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true)
    private String regionName;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Atlas atlas;

    @Setter
    @Column(name = "horizontal_axis", nullable = false)
    @Positive
    private int sizeXAxis;

    @Setter
    @Column(name = "vertical_axis", nullable = false)
    @Positive
    private int sizeYAxis;

    @OneToMany(mappedBy = "regionMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GameMap> gameMaps = new ArrayList<>();

}
