package com.example.backendproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backendproj.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
