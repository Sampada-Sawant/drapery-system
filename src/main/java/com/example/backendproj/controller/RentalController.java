package com.example.backendproj.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.backendproj.entity.Rental;
import com.example.backendproj.service.RentalService;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }

    //  BOOK ITEM
    @PostMapping
    public Rental rentItem(@RequestBody Rental rental) {
        return service.rentItem(rental);
    }

    //  RETURN ITEM
    @PutMapping("/return/{id}")
    public Rental returnItem(@PathVariable Long id) {
        return service.returnItem(id);
    }

    // GET ALL
    @GetMapping
    public List<Rental> getAllRentals() {
        return service.getAllRentals();
    }
}