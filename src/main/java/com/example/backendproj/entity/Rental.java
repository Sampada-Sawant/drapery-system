package com.example.backendproj.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long itemId;

    private LocalDate rentDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;

    private String status;
    private Double totalPrice;
}
