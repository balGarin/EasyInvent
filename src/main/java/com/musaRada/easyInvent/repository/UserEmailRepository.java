package com.musaRada.easyInvent.repository;

import com.musaRada.easyInvent.model.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEmailRepository extends JpaRepository<UserEmail,Long> {

    Optional<UserEmail> findByEmail(String email);
}
