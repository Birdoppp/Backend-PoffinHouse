package com.novi.poffinhouse.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserInputDto {
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 4, max = 100, message = "Name must be between 1 and 100 characters")
    private String username;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
}