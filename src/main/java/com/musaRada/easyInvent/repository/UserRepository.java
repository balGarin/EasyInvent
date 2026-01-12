package com.musaRada.easyInvent.repository;

import com.musaRada.easyInvent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
