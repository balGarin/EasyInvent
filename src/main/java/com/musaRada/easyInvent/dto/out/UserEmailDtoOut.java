package com.musaRada.easyInvent.dto.out;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserEmailDtoOut {
    private Long id;
    private String email;
}
