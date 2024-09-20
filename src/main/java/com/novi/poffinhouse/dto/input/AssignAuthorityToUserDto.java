package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.RoleEnum;
import lombok.Data;

@Data
public class AssignAuthorityToUserDto {
    private String username;
    private RoleEnum role;

}
