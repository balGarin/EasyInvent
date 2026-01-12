package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoIn {
    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    @Size(min = 8)
    private String password;
    @Email
    private String email;
}
