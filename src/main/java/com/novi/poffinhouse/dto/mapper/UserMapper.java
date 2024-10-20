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

    public static User toEntity(UserInputDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserOutputDto toOutputDto(User user) {
        if (user == null) {
            return null;
        }
        UserOutputDto outputDto = new UserOutputDto();
        outputDto.setId(user.getId());
        outputDto.setEmail(user.getEmail());
        outputDto.setUsername(user.getUsername());
        return outputDto;
    }

    public static List<UserOutputDto> toOutputDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public static AuthenticationRequest toLogInDto(User user) {
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