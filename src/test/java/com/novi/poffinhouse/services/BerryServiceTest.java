



package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.exceptions.BerryNotFoundException;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.gamemap.BerryPlantingSite;
import com.novi.poffinhouse.repositories.BerryPlantingSiteRepository;
import com.novi.poffinhouse.repositories.BerryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BerryServiceTest {

    @Mock
    private BerryRepository berryRepository;

    @Mock
    private BerryPlantingSiteRepository berryPlantingSiteRepository;

    @InjectMocks
    private BerryService berryService;

    private Berry berry;
    private BerryInputDto berryInputDto;

    @BeforeEach
    void setUp() {
        berry = new Berry();
        berry.setName("Cheri");
        berry.setIndexNumber(1L);
        berry.setDescription("A spicy berry");
        berry.setGrowthTime(24);
        berry.setValidated(false);
        berry.setSpicyPotency(10);
        berry.setDryPotency(0);
        berry.setBitterPotency(0);
        berry.setSourPotency(0);
        berry.setDryPotency(0);
        berry.setGames(List.of(new Game(),new Game()));

        berryInputDto = new BerryInputDto();
        berryInputDto.setName("Cheri");
        berryInputDto.setIndexNumber(1L);
        berryInputDto.setDescription("A spicy berry");
        berryInputDto.setGrowthTime(24);
        berryInputDto.setSpicyPotency(10);
        berryInputDto.setDryPotency(0);
        berryInputDto.setBitterPotency(0);
        berryInputDto.setSourPotency(0);
        berryInputDto.setDryPotency(0);
    }

    @Test
    void createBerry_ShouldSaveAndReturnBerryOutputDto() {
        // Arrange
        when(berryRepository.existsByNameIgnoreCase(anyString())).thenReturn(false);
        when(berryRepository.save(any(Berry.class))).thenReturn(berry);

        // Act
        BerryOutputDto result = berryService.createBerry(berryInputDto);

        // Assert
        assertEquals("Cheri", result.getName());
        verify(berryRepository, times(1)).save(any(Berry.class));
    }

    @Test
    void createBerry_ShouldThrowException_WhenBerryNameAlreadyExists() {
        // Arrange
        when(berryRepository.existsByNameIgnoreCase(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> berryService.createBerry(berryInputDto));
        verify(berryRepository, never()).save(any(Berry.class));
    }

    @Test
    void getBerryById_ShouldReturnBerryOutputDto() {
        // Arrange
        when(berryRepository.findById(anyLong())).thenReturn(Optional.of(berry));

        // Act
        BerryOutputDto result = berryService.getBerryById(1L);

        // Assert
        assertEquals("Cheri", result.getName());
        verify(berryRepository, times(1)).findById(anyLong());
    }

    @Test
    void getBerryById_ShouldThrowException_WhenBerryNotFound() {
        // Arrange
        when(berryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BerryNotFoundException.class, () -> berryService.getBerryById(1L));
        verify(berryRepository, times(1)).findById(anyLong());
    }

    @Test
    void getBerryByIndexNumber_ShouldReturnBerryOutputDto() {
        // Arrange
        when(berryRepository.findByIndexNumber(anyLong())).thenReturn(Optional.of(berry));

        // Act
        BerryOutputDto result = berryService.getBerryByIndexNumber(1L);

        // Assert
        assertEquals("Cheri", result.getName());
        verify(berryRepository, times(1)).findByIndexNumber(anyLong());
    }

    @Test
    void getBerryByIndexNumber_ShouldThrowException_WhenBerryNotFound() {
        // Arrange
        when(berryRepository.findByIndexNumber(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BerryNotFoundException.class, () -> berryService.getBerryByIndexNumber(1L));
        verify(berryRepository, times(1)).findByIndexNumber(anyLong());
    }

    @Test
    void getUnvalidatedBerries_ShouldReturnListOfBerryOutputDto() {
        // Arrange
        when(berryRepository.findUnvalidatedBerriesOrderedByIndexNumber()).thenReturn(List.of(berry));

        // Act
        List<BerryOutputDto> result = berryService.getUnvalidatedBerries();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Cheri", result.getFirst().getName());
        verify(berryRepository, times(1)).findUnvalidatedBerriesOrderedByIndexNumber();
    }

    @Test
    void getAllBerries_ShouldReturnListOfBerryOutputDto() {
        // Arrange
        when(berryRepository.findAll()).thenReturn(List.of(berry));

        // Act
        List<BerryOutputDto> result = berryService.getAllBerries();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Cheri", result.getFirst().getName());
        verify(berryRepository, times(1)).findAll();
    }

    @Test
    void getAllValidatedBerriesOrderedByIndexNumber_ShouldReturnListOfBerryOutputDto() {
        // Arrange
        when(berryRepository.findValidatedBerriesOrderedByIndexNumber()).thenReturn(List.of(berry));

        // Act
        List<BerryOutputDto> result = berryService.getAllValidatedBerriesOrderedByIndexNumber();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Cheri", result.getFirst().getName());
        verify(berryRepository, times(1)).findValidatedBerriesOrderedByIndexNumber();
    }

    @Test
    void updateBerry_ShouldUpdateAndReturnBerryOutputDto() {
        // Arrange
        when(berryRepository.findByIndexNumber(anyLong())).thenReturn(Optional.of(berry));
        when(berryRepository.save(any(Berry.class))).thenReturn(berry);

        // Act
        BerryOutputDto result = berryService.updateBerry(1L, berryInputDto);

        // Assert
        assertEquals("Cheri", result.getName());
        verify(berryRepository, times(1)).save(any(Berry.class));
    }

    @Test
    void validateBerry_ShouldSetValidatedTrueAndReturnBerryOutputDto() {
        // Arrange
        berry.setValidated(false);
        when(berryRepository.findByIndexNumber(anyLong())).thenReturn(Optional.of(berry));

        // Act
        BerryOutputDto result = berryService.validateBerry(1L);

        // Assert
        assertTrue(result.getValidated());
        verify(berryRepository, times(1)).save(any(Berry.class));
    }

    @Test
    void deleteBerry_ShouldDeleteBerryAndReturnSuccessMessage() {
        // Arrange
        BerryPlantingSite berryPlantingSite = new BerryPlantingSite();
        berryPlantingSite.setSoilSlots(2);
        berryPlantingSite.getPlantedBerriesBySlots().put(1, berry);
        when(berryRepository.findByIndexNumber(anyLong())).thenReturn(Optional.of(berry));
        when(berryPlantingSiteRepository.findBerryPlantingSiteByPlantedBerriesBySlotsEquals(any(Berry.class)))
                .thenReturn(List.of(berryPlantingSite));

        // Act
        String result = berryService.deleteBerry(1L);

        // Assert
        assertEquals("Cheri berry deleted successfully", result);
        verify(berryRepository, times(1)).deleteByIndexNumber(anyLong());
    }

    @Test
    void deleteBerry_ShouldThrowException_WhenBerryNotFound() {
        // Arrange
        when(berryRepository.findByIndexNumber(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BerryNotFoundException.class, () -> berryService.deleteBerry(1L));
        verify(berryRepository, never()).deleteByIndexNumber(anyLong());
    }
}
