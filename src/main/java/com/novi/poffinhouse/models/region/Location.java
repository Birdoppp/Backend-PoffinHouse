package com.novi.poffinhouse.models.region;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    @Column
    private String name;
    @Setter
    private String description;
    @Setter
    @Column(nullable = false, name = "Horizontal Axis")
    private int coordinateX;
    @Setter
    @Column(nullable = false, name = "Vertical Axis")
    private int coordinateY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    @Setter
    private Map map;
}
