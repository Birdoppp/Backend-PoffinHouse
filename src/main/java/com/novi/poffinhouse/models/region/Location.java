package com.novi.poffinhouse.models.region;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Column(unique = true)
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
    @JoinColumn(name = "map_id")  // Explicitly specifying the foreign key column
    private Map map;
}
