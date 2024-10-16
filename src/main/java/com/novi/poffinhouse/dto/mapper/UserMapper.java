package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.UserInputDto;
import com.novi.poffinhouse.dto.authenticate.AuthenticationRequest;
import com.novi.poffinhouse.dto.output.UserOutputDto;
import com.novi.poffinhouse.models.auth.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserInputDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserOutputDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserOutputDto dto = new UserOutputDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public List<UserOutputDto> toDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AuthenticationRequest toLogInDto(User user) {
        if (user == null) {
            return null;
        }
        AuthenticationRequest dto = new AuthenticationRequest();
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setAuthorities(user.getAuthorities());
        return dto;
    }
}