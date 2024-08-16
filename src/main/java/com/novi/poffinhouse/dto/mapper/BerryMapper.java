package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.models.berries.Berry;

public class BerryMapper {

    public static Berry toEntity(BerryInputDto dto) {
        Berry berry = new Berry();
        berry.setName(dto.getName());
        berry.setIndexNumber(dto.getIndexNumber());
        berry.setDescription(dto.getDescription());
        berry.setGrowthTime(dto.getGrowthTime());
        berry.setCategoryType(dto.getCategoryType());
        berry.setSpicyPotency(dto.getSpicyPotency());
        berry.setDryPotency(dto.getDryPotency());
        berry.setSweetPotency(dto.getSweetPotency());
        berry.setBitterPotency(dto.getBitterPotency());
        berry.setSourPotency(dto.getSourPotency());
        return berry;
    }

    public static BerryOutputDto toOutputDto(Berry berry) {
        BerryOutputDto dto = new BerryOutputDto();
        dto.setId(berry.getId());
        dto.setName(berry.getName());
        dto.setIndexNumber(berry.getIndexNumber());
        dto.setDescription(berry.getDescription());
        dto.setGrowthTime(berry.getGrowthTime());
        dto.setCategoryType(berry.getCategoryType());
        dto.setSpicyPotency(berry.getSpicyPotency());
        dto.setDryPotency(berry.getDryPotency());
        dto.setSweetPotency(berry.getSweetPotency());
        dto.setBitterPotency(berry.getBitterPotency());
        dto.setSourPotency(berry.getSourPotency());
        return dto;
    }
}
