package com.example.backendproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backendproj.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
