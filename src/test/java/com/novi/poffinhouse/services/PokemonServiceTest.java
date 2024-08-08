//package com.novi.poffinhouse.services;
//
//import com.novi.poffinhouse.dto.input.PokemonInputDto;
//import com.novi.poffinhouse.dto.output.PokemonOutputDto;
//import com.novi.poffinhouse.dto.mapper.PokemonMapper;
//import com.novi.poffinhouse.models.pokemon.Pokemon;
//import com.novi.poffinhouse.repositories.PokemonRepository;
//import com.novi.poffinhouse.util.TypeEnum;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PokemonServiceTest {
//
//    @InjectMocks
//    private PokemonService pokemonService;
//
//    @Mock
//    private PokemonRepository pokemonRepository;
//
//    @Mock
//    private PokemonMapper pokemonMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSavePokemon_FairyTypeGenerationLessThanSix_ThrowsException() {
//        PokemonInputDto inputDto = new PokemonInputDto();
//        inputDto.setName("Clefairy");
//        inputDto.setNationalDex(35);
//        inputDto.setGeneration(5);
//        inputDto.setType(TypeEnum.POKEMON_TYPE.FAIRY);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            pokemonService.savePokemon(inputDto);
//        });
//    }
//
//    @Test
//    public void testSavePokemon_DarkTypeGenerationLessThanTwo_ThrowsException() {
//        PokemonInputDto inputDto = new PokemonInputDto();
//        inputDto.setName("Umbreon");
//        inputDto.setNationalDex(197);
//        inputDto.setGeneration(1);
//        inputDto.setType(TypeEnum.POKEMON_TYPE.DARK);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            pokemonService.savePokemon(inputDto);
//        });
//    }
//
//    @Test
//    public void testSavePokemon_GhostTypeGenerationLessThanTwo_ThrowsException() {
//        PokemonInputDto inputDto = new PokemonInputDto();
//        inputDto.setName("Gengar");
//        inputDto.setNationalDex(94);
//        inputDto.setGeneration(1);
//        inputDto.setType(TypeEnum.POKEMON_TYPE.GHOST);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            pokemonService.savePokemon(inputDto);
//        });
//    }
//
//    @Test
//    public void testSavePokemon_ValidPokemon_SavesSuccessfully() {
//        PokemonInputDto inputDto = new PokemonInputDto();
//        inputDto.setName("Pikachu");
//        inputDto.setNationalDex(25);
//        inputDto.setGeneration(1);
//        inputDto.setType(TypeEnum.POKEMON_TYPE.ELECTRIC);
//
//        Pokemon pokemon = new Pokemon();
//        pokemon.setName("Pikachu");
//        pokemon.setNationalDex(25);
//        pokemon.setGeneration(1);
//        pokemon.setType(TypeEnum.POKEMON_TYPE.ELECTRIC);
//
//        PokemonOutputDto outputDto = new PokemonOutputDto();
//        outputDto.setId(1L);
//        outputDto.setName("Pikachu");
//        outputDto.setNationalDex(25);
//        outputDto.setGeneration(1);
//        outputDto.setType(TypeEnum.POKEMON_TYPE.ELECTRIC);
//
//        when(pokemonMapper.toEntity(inputDto)).thenReturn(pokemon);
//        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);
//        when(pokemonMapper.toOutputDto(pokemon)).thenReturn(outputDto);
//
//        PokemonOutputDto result = pokemonService.savePokemon(inputDto);
//
//        assertEquals(outputDto, result);
//    }
//}
