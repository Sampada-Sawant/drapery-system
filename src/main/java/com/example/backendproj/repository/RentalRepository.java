package com.example.backendproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backendproj.entity.Rental;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    // Check overlapping bookings
    List<Rental> findByItemIdAndRentDateLessThanEqualAndReturnDateGreaterThanEqual(
            Long itemId,
            LocalDate returnDate,
            LocalDate rentDate
    );
}