package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.UserInputDto;
import com.novi.poffinhouse.dto.authenticate.AuthenticationRequest;
import com.novi.poffinhouse.dto.output.UserOutputDto;
import com.novi.poffinhouse.dto.mapper.UserMapper;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

import static com.novi.poffinhouse.config.SpringSecurityConfig.passwordEncoder;

@Validated
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        User user = userMapper.toEntity(userInputDto);
        user.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
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
        return userMapper.toDto(user);
    }

    public AuthenticationRequest logInUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return userMapper.toLogInDto(user);
    }

    public UserOutputDto updateUser(Long id, UserInputDto userInputDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

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
        return userMapper.toDto(updatedUser);
    }

    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found."));
        String username = user.getUsername();
        userRepository.deleteById(id);
        return "User with username:'" + username + "' has been deleted successfully. Including all associated data.";
    }




//For TeamService
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return user.get();
    }
}