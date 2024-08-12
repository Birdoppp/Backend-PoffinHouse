//Doesn't contain new pokemon data yet

//package com.novi.poffinhouse.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.novi.poffinhouse.dto.input.PokemonInputDto;
//import com.novi.poffinhouse.dto.output.PokemonOutputDto;
//import com.novi.poffinhouse.services.PokemonService;
//import com.novi.poffinhouse.util.TypeEnum;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(PokemonController.class)
//public class PokemonControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PokemonService pokemonService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testCreatePokemon_ValidPokemon_ReturnsCreated() throws Exception {
//        PokemonInputDto inputDto = new PokemonInputDto();
//        inputDto.setName("Pikachu");
//        inputDto.setNationalDex(25);
//        inputDto.setType(TypeEnum.POKEMON_TYPE.ELECTRIC);
//
//        PokemonOutputDto outputDto = new PokemonOutputDto();
//        outputDto.setId(1L);
//        outputDto.setName("Pikachu");
//        outputDto.setNationalDex(25);
//        ;
//        outputDto.setType(TypeEnum.POKEMON_TYPE.ELECTRIC);
//
//        Mockito.when(pokemonService.createPokemon(Mockito.any(PokemonInputDto.class))).thenReturn(outputDto);
//
//        mockMvc.perform(post("/pokemon")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(inputDto)))
//                .andExpect(status().isCreated()); // Ensure to return 201 Created in the controller
//    }
//}
