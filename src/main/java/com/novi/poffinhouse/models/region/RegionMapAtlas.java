package com.novi.poffinhouse.models.region;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "atlas")
@Entity
public class RegionMapAtlas {
    @Id
    private String fileName;

    public RegionMapAtlas(String fileName) {
        this.fileName = fileName;
    }
}