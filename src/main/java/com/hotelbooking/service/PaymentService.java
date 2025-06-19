package com.hotelbooking.system.service;

import com.hotelbooking.system.model.Booking;
import com.hotelbooking.system.model.Payment;
import com.hotelbooking.system.repository.BookingRepository;
import com.hotelbooking.system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository; // To fetch Booking by ID

    // Create
    public Payment createPayment(Payment payment) {
        // Ensure associated Booking exists before saving the payment
        if (payment.getBooking() != null && payment.getBooking().getId() != null) {
            Booking booking = bookingRepository.findById(payment.getBooking().getId())
                    .orElseThrow(() -> new RuntimeException("Booking not found for ID: " + payment.getBooking().getId()));
            payment.setBooking(booking);
        } else {
            throw new RuntimeException("Payment must be associated with an existing Booking.");
        }
        return paymentRepository.save(payment);
    }

    // Read all
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Read by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Update
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id)); // Custom exception recommended

        // Update fields
        payment.setAmount(paymentDetails.getAmount());
        payment.setPaymentDate(paymentDetails.getPaymentDate());
        payment.setPaymentMethod(paymentDetails.getPaymentMethod());
        payment.setTransactionId(paymentDetails.getTransactionId());
        payment.setPaymentStatus(paymentDetails.getPaymentStatus());

        // Handle updates for associated Booking if it's part of the update request
        if (paymentDetails.getBooking() != null && paymentDetails.getBooking().getId() != null) {
            Booking booking = bookingRepository.findById(paymentDetails.getBooking().getId())
                    .orElseThrow(() -> new RuntimeException("Booking not found for ID: " + paymentDetails.getBooking().getId()));
            payment.setBooking(booking);
        }

        return paymentRepository.save(payment);
    }

    // Delete
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id); // Custom exception recommended
        }
        paymentRepository.deleteById(id);
    }
}