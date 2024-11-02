package com.novi.poffinhouse.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.util.TypeEnum;
import jakarta.transaction.Transactional;
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
@Transactional
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
        berryInputDto.setIndexNumber(9999L);// high number to avoid conflicts
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
                .andExpect(jsonPath("$.name").value("Testberry")) //the value is formatted by Capitalize class
                .andExpect(jsonPath("$.description").value("A test berry"))
                .andExpect(jsonPath("$.indexNumber").value(9999L))
                .andExpect(jsonPath("$.growthTime").value(24))
                .andExpect(jsonPath("$.categoryType").value("MEDICINE"))
                .andExpect(jsonPath("$.spicyPotency").value(10))
                .andExpect(jsonPath("$.dryPotency").value(0))
                .andExpect(jsonPath("$.sweetPotency").value(0))
                .andExpect(jsonPath("$.bitterPotency").value(0))
                .andExpect(jsonPath("$.sourPotency").value(0));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void getBerryById_ShouldReturnBerry() throws Exception {
        String response = mockMvc.perform(post("/berries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(berryInputDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long createdBerryId = objectMapper.readTree(response).get("id").asLong(); // Extract Id from response

        mockMvc.perform(get("/berries/id/" + createdBerryId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdBerryId));
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