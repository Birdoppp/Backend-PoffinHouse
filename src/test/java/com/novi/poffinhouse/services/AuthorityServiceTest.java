package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.Authority;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.AuthorityRepository;
import com.novi.poffinhouse.repositories.UserRepository;
import com.novi.poffinhouse.util.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorityServiceTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorityService authorityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignAuthority_UserNotFound() {
        when(userService.findByUsername("testuser")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> authorityService.assignAuthority("testuser", RoleEnum.TRAINER));
    }

    @Test
    void testAssignAuthority_Success() {
        User user = new User();
        user.setUsername("testuser");

        when(userService.findByUsername("testuser")).thenReturn(user);
        when(authorityRepository.save(any(Authority.class))).thenReturn(new Authority());

        AuthorityOutputDto authorityDto = authorityService.assignAuthority("testuser", RoleEnum.TRAINER);

        assertNotNull(authorityDto);
        verify(authorityRepository, times(1)).save(any(Authority.class));
    }

    @Test
    void testGetAuthoritiesByUsername_UserNotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authorityService.getAuthoritiesByUsername("testuser"));
    }

    @Test
    void testRemoveAllAuthorities_Success() {
        User user = new User();
        user.setUsername("testuser");

        when(userService.findByUsername("testuser")).thenReturn(user);

        String result = authorityService.removeAllAuthorities("testuser");

        assertEquals("Authorization has been removed for testuser", result);
        verify(authorityRepository, times(1)).deleteByUsername("testuser");
    }
}
