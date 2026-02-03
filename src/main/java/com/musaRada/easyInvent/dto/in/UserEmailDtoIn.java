package com.musaRada.easyInvent.dto.in;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserEmailDtoIn {
    @Email
    private String email;
}
