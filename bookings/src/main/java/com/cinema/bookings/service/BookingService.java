package com.cinema.bookings.service;

import com.cinema.bookings.model.Booking;
import com.cinema.bookings.model.Ticket;
import com.cinema.bookings.repository.BookingRepository;
import com.cinema.bookings.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    @Autowired
    TicketRepository tRepository;

    public List<Booking> listAll() {
        return repository.findAll();
    }

    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    public Booking get(Long id) {
        return repository.getOne(id);
    }

    public List<Booking> searchByBookingNo(String bookingNo) {
        return repository.findByBookingNo(bookingNo);
    }

    public void saveTickets(List<Ticket> tickets) {
        tRepository.saveAll(tickets);
    }

    public List<Booking> searchByUsername(String username) {
        return repository.findByUsername(username);
    }
}
