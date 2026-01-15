package com.musaRada.easyInvent.dto.out;

import lombok.Data;

@Data
public class UserDtoOut {
    private Long id;
    private String username;
    private String password;
    private String email;
}
