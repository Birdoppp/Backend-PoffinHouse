package com.novi.poffinhouse.models.game;

import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.models.region.RegionMap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class GameMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_map_id", nullable = false)
    private RegionMap regionMap;

    @Setter
    @OneToMany(mappedBy = "gameMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();

}
