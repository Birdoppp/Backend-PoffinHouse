package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.UserInputDto;
import com.novi.poffinhouse.dto.authenticate.AuthenticationRequest;
import com.novi.poffinhouse.dto.output.UserOutputDto;
import com.novi.poffinhouse.dto.mapper.UserMapper;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.UserRepository;
import com.novi.poffinhouse.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import static com.novi.poffinhouse.config.SpringSecurityConfig.passwordEncoder;
import static com.novi.poffinhouse.dto.mapper.UserMapper.toOutputDto;

@Validated
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        User user = UserMapper.toEntity(userInputDto);
        user.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
        User savedUser = userRepository.save(user);
        return toOutputDto(savedUser);
    }

    public AuthenticationRequest logInUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return UserMapper.toLogInDto(user);
    }

    public UserOutputDto getUserBy(String field, String value) {
        User user;
        switch (field) {
            case "id" -> user = userRepository.findById(Long.valueOf(value))
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + value));
            case "username" -> user = userRepository.findByUsername(value)
                    .orElseThrow(() -> new UserNotFoundException("User not found with username: " + value));
            case "email" -> user = userRepository.findByEmail(value)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + value));
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
        return UserMapper.toOutputDto(user);
    }

    public List<UserOutputDto> getUsers() {
        if (AuthUtil.isAdmin()) {
            List<User> users = userRepository.findAll();
            return UserMapper.toOutputDtoList(users);

        } else {
            User currentUser = userRepository.findByUsername(AuthUtil.getCurrentUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            return List.of(UserMapper.toOutputDto(currentUser));
        }
    }

    public UserOutputDto updateUser(String username, UserInputDto userInputDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        if (!AuthUtil.isAdminOrOwner(username)) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }

        if (userInputDto.getEmail() != null) {
            user.setEmail(userInputDto.getEmail());
        }
        if (userInputDto.getUsername() != null) {
            user.setUsername(userInputDto.getUsername());
        }
        if (userInputDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return UserMapper.toOutputDto(updatedUser);
    }

    public String deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found."));
        userRepository.deleteById(user.getId());
        return "User with username:'" + username + "' has been deleted successfully. Including all associated data.";
    }


    //For AuthorityService
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return user.get();
    }
}