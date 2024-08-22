package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.models.berries.Berry;

public class BerryMapper {

    public static Berry toEntity(BerryInputDto inputDto) {
        Berry berry = new Berry();
        berry.setName(inputDto.getName());
        berry.setIndexNumber(inputDto.getIndexNumber());
        berry.setDescription(inputDto.getDescription());
        berry.setGrowthTime(inputDto.getGrowthTime());
        berry.setCategoryType(inputDto.getCategoryType());
        berry.setSpicyPotency(inputDto.getSpicyPotency());
        berry.setDryPotency(inputDto.getDryPotency());
        berry.setSweetPotency(inputDto.getSweetPotency());
        berry.setBitterPotency(inputDto.getBitterPotency());
        berry.setSourPotency(inputDto.getSourPotency());
        return berry;
    }

    public static BerryOutputDto toOutputDto(Berry berry) {
        BerryOutputDto outputDto = new BerryOutputDto();
        outputDto.setId(berry.getId());
        outputDto.setName(berry.getName());
        outputDto.setIndexNumber(berry.getIndexNumber());
        outputDto.setDescription(berry.getDescription());
        outputDto.setGrowthTime(berry.getGrowthTime());
        outputDto.setCategoryType(berry.getCategoryType());
        outputDto.setSpicyPotency(berry.getSpicyPotency());
        outputDto.setDryPotency(berry.getDryPotency());
        outputDto.setSweetPotency(berry.getSweetPotency());
        outputDto.setBitterPotency(berry.getBitterPotency());
        outputDto.setSourPotency(berry.getSourPotency());
        return outputDto;
    }
}
