package com.musaRada.easyInvent.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    @OneToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "email_id")
    private UserEmail email;

}
