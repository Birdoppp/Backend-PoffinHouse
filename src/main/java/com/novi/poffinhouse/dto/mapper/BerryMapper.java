package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.models.berries.Berry;

import java.util.ArrayList;
import java.util.List;

public class BerryMapper {

    //      Dto to model
    public static Berry berryFromInputDtoToModel(BerryInputDto berryInputDto, int id) {
        Berry berry = new Berry();
        berryInputDto.setName(berry.getName());
        berryInputDto.setIndexNumber(berry.getIndexNumber());
        berryInputDto.setDescription(berry.getDescription());
        berryInputDto.setGrowthTime(berry.getGrowthTime());
        berryInputDto.setCategoryType(berry.getCategoryType());
        berryInputDto.setSpicyPotency(berry.getSpicyPotency());
        berryInputDto.setDryPotency(berry.getDryPotency());
        berryInputDto.setSweetPotency(berry.getSweetPotency());
        berryInputDto.setBitterPotency(berry.getBitterPotency());
        berryInputDto.setSourPotency(berry.getSourPotency());

        return berry;
    }

    //    Model to dto
    public static BerryOutputDto berryFromModelToOutput(Berry berry) {
        BerryOutputDto berryOutputDto = new BerryOutputDto();
        berryOutputDto.setName(berry.getName());
        berryOutputDto.setIndexNumber(berry.getIndexNumber());
        berryOutputDto.setDescription(berry.getDescription());
        berryOutputDto.setGrowthTime(berry.getGrowthTime());
        berryOutputDto.setCategoryType(berry.getCategoryType());
        berryOutputDto.setSpicyPotency(berry.getSpicyPotency());
        berryOutputDto.setDryPotency(berry.getDryPotency());
        berryOutputDto.setSweetPotency(berry.getSweetPotency());
        berryOutputDto.setBitterPotency(berry.getBitterPotency());
        berryOutputDto.setSourPotency(berry.getSourPotency());

        return berryOutputDto;
    }

//    List to list
    public static List<BerryOutputDto> berryModelListToOutputList(List<Berry> berries){
        List<BerryOutputDto> berryOutputDtoList = new ArrayList<>();
      berries.forEach((berry) -> berryOutputDtoList.add(berryFromModelToOutput(berry)));
      return berryOutputDtoList;
    }
}