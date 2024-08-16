package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.util.TypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BerryServiceTests {

    @Autowired
    private BerryService berryService;

    @Test

    void testCreateBerry() {
        BerryInputDto input = new BerryInputDto();
        input.setName("Cheri");
        input.setIndexNumber(1);
        input.setDescription("A spicy berry that cures paralysis.");
        input.setGrowthTime(12);
        input.setCategoryType(TypeEnum.BERRY_CATEGORY_TYPE.MEDICINE);
        input.setSpicyPotency(10);
        input.setDryPotency(0);
        input.setSweetPotency(0);
        input.setBitterPotency(0);
        input.setSourPotency(0);

        BerryOutputDto result = berryService.createBerry(input);

        assertNotNull(result.getId());
        assertEquals("Cheri", result.getName());
        assertEquals(1, result.getIndexNumber());
        assertEquals("A spicy berry that cures paralysis.", result.getDescription());
        assertEquals(12, result.getGrowthTime());
        assertEquals(TypeEnum.BERRY_CATEGORY_TYPE.MEDICINE, result.getCategoryType());
        assertEquals(10, result.getSpicyPotency());
    }
}
