package com.novi.poffinhouse.models.pokemon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Pokemon {
    @Id
    private int id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private int nationalDex;
    @Column(nullable = false)
    private int generation;


}
