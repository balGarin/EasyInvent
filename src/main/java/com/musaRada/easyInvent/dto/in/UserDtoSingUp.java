package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDtoSingUp {
    @NotBlank(message = "Поле username не может быть пустым")
    @NotNull(message = "Поле username не может быть пустым")
    private String username;
    @NotBlank(message = "Поле password не может быть пустым")
    @NotNull(message = "Поле password не может быть пустым")
    @Size(min = 8)
    private String password;
    @Email(message = "Нужно указать корректный Email")
    @NotNull(message = "Поле не может быть пустым")
    @NotBlank(message = "Поле не может быть пустым")
    private String email;
}
