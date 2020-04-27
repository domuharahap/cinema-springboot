package com.cinema.bookings.repository;

import com.cinema.bookings.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBookingNo(String bookingNo);

    List<Booking> findByUsername(String username);
}
