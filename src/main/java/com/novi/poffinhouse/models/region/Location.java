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
    @Column(nullable = false)
    private int coordinateX;
    @Setter
    @Column(nullable = false)
    private int coordinateY;

}
