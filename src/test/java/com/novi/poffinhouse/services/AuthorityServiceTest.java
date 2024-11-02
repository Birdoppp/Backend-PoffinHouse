package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.AssignAuthorityToUserDto;
import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.exceptions.AccessDeniedException;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.Authority;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.AuthorityRepository;
import com.novi.poffinhouse.repositories.UserRepository;
import com.novi.poffinhouse.util.AuthUtil;
import com.novi.poffinhouse.util.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private AuthorityService authorityService;

    @Mock
    Authentication authentication;

    private User user;
    private Authority authority;
    private AssignAuthorityToUserDto assignAuthorityDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setEmail("test@test.com");
        user.setPassword("password");

        authority = new Authority();
        authority.setAuthority(RoleEnum.ROLE_TRAINER);
        authority.setUsername("testUser");
        authority.setUser(user);

        assignAuthorityDto = new AssignAuthorityToUserDto("testUser", RoleEnum.ROLE_TRAINER);

        authentication.setAuthenticated(true);
    }

    @Test
    void assignAuthority_ShouldReturnAuthorityOutputDto() {
        // Arrange
        when(userService.findByUsername("testUser")).thenReturn(user);
        when(authorityRepository.save(any(Authority.class))).thenReturn(authority);
        try (MockedStatic<AuthUtil> authUtil = Mockito.mockStatic(AuthUtil.class)) {
            authUtil.when(() -> AuthUtil.isAdminOrOwner(anyString())).thenReturn(true);
            authUtil.when(AuthUtil::isAdmin).thenReturn(false);
            authUtil.when(AuthUtil::getCurrentUsername).thenReturn("testUser");

            // Act
            AuthorityOutputDto result = authorityService.assignAuthority(assignAuthorityDto);

            // Assert
            assertNotNull(result);
            assertEquals(RoleEnum.ROLE_TRAINER.toString(), result.getAuthority());
            verify(authorityRepository, times(1)).save(any(Authority.class));
        }
    }


    @Test
    void assignAuthority_ShouldThrowIllegalArgumentException_WhenUsernameIsNull() {
        // Arrange
        assignAuthorityDto.setUsername(null);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> authorityService.assignAuthority(assignAuthorityDto));
        assertEquals("Username and role must not be null", thrown.getMessage());
    }

    @Test
    void assignAuthority_ShouldThrowIllegalArgumentException_WhenRoleIsNull() {
        // Arrange
        assignAuthorityDto.setRole(null);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> authorityService.assignAuthority(assignAuthorityDto));
        assertEquals("Username and role must not be null", thrown.getMessage());
    }


    @Test
    @WithMockUser(username = "testUser", roles = {"ADMIN"})
    void getAuthoritiesByUsername_ShouldReturnAuthoritySet_WhenUserExists() {
        // Arrange
        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(authority);

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        when(authorityRepository.findByUsername("testUser")).thenReturn(authoritySet);

        try (MockedStatic<AuthUtil> authUtil = Mockito.mockStatic(AuthUtil.class)) {
            authUtil.when(() -> AuthUtil.isAdminOrOwner(anyString())).thenReturn(true);
            authUtil.when(AuthUtil::isAdmin).thenReturn(false);
            authUtil.when(AuthUtil::getCurrentUsername).thenReturn("testUser");

            // Act
            Set<AuthorityOutputDto> result = authorityService.getAuthoritiesByUsername("testUser");

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(authorityRepository, times(1)).findByUsername("testUser");
        }
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"TRAINER"})
    void getAuthoritiesByUsername_ShouldThrowAccessDeniedException_WhenUserIsAdminOrOwner() {
        // Arrange
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        try (MockedStatic<AuthUtil> authUtil = Mockito.mockStatic(AuthUtil.class)) {
            authUtil.when(() -> AuthUtil.isAdminOrOwner("testUser")).thenReturn(false);
            authUtil.when(AuthUtil::isAdmin).thenReturn(false);
            // Act & Assert
            assertThrows(AccessDeniedException.class, () -> authorityService.getAuthoritiesByUsername("testUser"));
        }
    }

    @Test
    void getAuthoritiesByUsername_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(userRepository.findByUsername("Unknown")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> authorityService.getAuthoritiesByUsername("Unknown"));
    }

    @Test
    void removeAllAuthorities_ShouldReturnSuccessMessage_WhenUserExists() {
        // Arrange
        user.setAuthorities(new HashSet<>());

        when(userService.findByUsername("testUser")).thenReturn(user);

        // Act
        String result = authorityService.removeAllAuthorities("testUser");

        // Assert
        assertEquals("Authorization has been removed for testUser", result);
        assertTrue(user.getAuthorities().isEmpty());
    }

    @Test
    void removeAllAuthorities_ShouldThrowIllegalArgumentException_WhenUsernameIsNull() {
        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> authorityService.removeAllAuthorities(null));
        assertEquals("Username must not be null", thrown.getMessage());
    }

}
