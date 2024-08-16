package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.UserInputDto;
import com.novi.poffinhouse.dto.mapper.UserMapper;
import com.novi.poffinhouse.dto.output.UserOutputDto;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        UserInputDto userInputDto = new UserInputDto();
        User user = new User();
        User savedUser = new User();
        UserOutputDto userOutputDto = new UserOutputDto();

        when(userMapper.toEntity(userInputDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toDto(savedUser)).thenReturn(userOutputDto);

        UserOutputDto result = userService.createUser(userInputDto);

        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserBy_Username_Success() {
        // Arrange: Create a mock User and UserOutputDto
        User user = new User();
        user.setUsername("testuser");

        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setUsername("testuser");

        // Mock the behavior of userRepository and userMapper
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userOutputDto);

        // Act: Call the service method
        UserOutputDto result = userService.getUserBy("username", "testuser");

        // Assert: Check the results
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void testGetUserBy_Id_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserBy("id", "1"));
    }

    @Test
    void testUpdateUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, new UserInputDto()));
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
