package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.models.auth.Authority;
import com.novi.poffinhouse.dto.input.AuthorityInputDto;
import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper {

    private final UserRepository userRepository;

    public AuthorityMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Authority toEntity(AuthorityInputDto dto) {
        if (dto == null) {
            return null;
        }

        Authority authority = new Authority();
        authority.setAuthority(dto.getAuthority());
        authority.setUsername(dto.getUsername());
        authority.setUser(userRepository.findByUsername(dto.getUsername()).get());

        return authority;
    }

    public static AuthorityOutputDto toOutputDto(Authority authority) {
        if (authority == null) {
            return null;
        }

        AuthorityOutputDto dto = new AuthorityOutputDto();
        dto.setId(authority.getId());
        dto.setAuthority(authority.getAuthority());
        dto.setUsername(authority.getUsername());

        if (authority.getUser() != null) {
            dto.setUserID(String.valueOf(authority.getUser().getId()));
        }

        return dto;
    }
}
