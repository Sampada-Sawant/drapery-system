package com.example.backendproj.service;

import org.springframework.stereotype.Service;
import com.example.backendproj.entity.*;
import com.example.backendproj.repository.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepo;
    private final ItemRepository itemRepo;

    public RentalService(RentalRepository rentalRepo, ItemRepository itemRepo) {
        this.rentalRepo = rentalRepo;
        this.itemRepo = itemRepo;
    }

    // 🔥 BOOK ITEM
    public Rental rentItem(Rental rental) {

        // 1. Check date validity
        if (rental.getReturnDate().isBefore(rental.getRentDate())) {
            throw new RuntimeException("Return date must be after rent date");
        }

        // 2. Check availability (IMPORTANT 🔥)
        List<Rental> conflicts = rentalRepo
                .findByItemIdAndRentDateLessThanEqualAndReturnDateGreaterThanEqual(
                        rental.getItemId(),
                        rental.getReturnDate(),
                        rental.getRentDate()
                );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Item already booked for selected dates");
        }

        // 3. Calculate price
        Item item = itemRepo.findById(rental.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        long days = ChronoUnit.DAYS.between(
                rental.getRentDate(),
                rental.getReturnDate()
        );

        rental.setTotalPrice(days * item.getPricePerDay());
        rental.setStatus("BOOKED");

        return rentalRepo.save(rental);
    }

    // 🔄 RETURN ITEM
    public Rental returnItem(Long rentalId) {
        Rental rental = rentalRepo.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setActualReturnDate(LocalDate.now());

        if (rental.getActualReturnDate().isAfter(rental.getReturnDate())) {
            rental.setStatus("LATE");
        } else {
            rental.setStatus("RETURNED");
        }

        return rentalRepo.save(rental);
    }

    // 📄 GET ALL RENTALS
    public List<Rental> getAllRentals() {
        return rentalRepo.findAll();
    }
}