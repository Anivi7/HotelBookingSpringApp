package com.hotelbooking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod; // e.g., Credit Card, PayPal
    private String transactionId;
    private String paymentStatus; // e.g., PENDING, COMPLETED, FAILED
}

public class Payment {
    
}
