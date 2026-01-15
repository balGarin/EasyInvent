package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoSingIn {
    @NotBlank(message = "Поле username не может быть пустым")
    @NotBlank(message = "Поле username не может быть пустым")
    private String username;
    @NotBlank(message = "Поле password не может быть пустым")
    @NotBlank(message = "Поле password не может быть пустым")
    @Size(min = 8,message = "Длина пароля - минимум 8 символов")
    private String password;

}
