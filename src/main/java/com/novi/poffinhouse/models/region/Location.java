package com.novi.poffinhouse.models.region;

import com.novi.poffinhouse.models.game.GameMap;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_map_id", nullable = false)
    private GameMap gameMap;
    @Setter
    @PositiveOrZero
    @Column(nullable = false, name = "coordinate_X")
    private int coordinateX;
    @Setter
    @PositiveOrZero
    @Column(nullable = false, name = "coordinate_Y")
    private int coordinateY;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    private List<BerryPlantingSite> berryPlantingSites = new ArrayList<>();


}
