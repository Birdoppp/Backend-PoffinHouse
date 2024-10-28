package com.novi.poffinhouse.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.util.TypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BerryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BerryInputDto berryInputDto;

    @BeforeEach
    void setUp() {
        berryInputDto = new BerryInputDto();
        berryInputDto.setName("TestBerry");
        berryInputDto.setDescription("A test berry");
        berryInputDto.setIndexNumber(999L);
        berryInputDto.setGrowthTime(24);
        berryInputDto.setCategoryType(TypeEnum.BERRY_CATEGORY_TYPE.MEDICINE);
        berryInputDto.setSpicyPotency(10);
        berryInputDto.setDryPotency(0);
        berryInputDto.setSweetPotency(0);
        berryInputDto.setBitterPotency(0);
        berryInputDto.setSourPotency(0);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createBerry_ShouldReturnCreatedBerry() throws Exception {
        mockMvc.perform(post("/berries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(berryInputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Testberry"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getBerryById_ShouldReturnBerry() throws Exception {
        mockMvc.perform(get("/berries/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllBerries_ShouldReturnListOfBerries() throws Exception {
        mockMvc.perform(get("/berries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}