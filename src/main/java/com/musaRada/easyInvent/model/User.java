package com.musaRada.easyInvent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;

}
