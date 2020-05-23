package com.cinema.bookings.controller;

import com.cinema.bookings.model.Booking;
import com.cinema.bookings.model.Ticket;
import com.cinema.bookings.service.BookingService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/booking", produces = "application/json")
public class BookingController {
    private static final String HEADER = "Origin, X-Requested-With, Content-Type, Accept";
    private static final String ALLOW_HEADER = "Access-Control-Allow-Headers";

    @Autowired
    BookingService service;

    @GetMapping
    public ResponseEntity<List<Booking>> bookings(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        return ResponseEntity.ok().headers(responseHeaders).body(service.listAll()) ;
    }

    @PutMapping
    public ResponseEntity<Booking> update(@RequestBody Booking booking) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        Booking b = service.save(booking);

        if (b == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(b);
    }

    @PostMapping(params = {"noOfTickets"})
    public ResponseEntity<Booking> add(@RequestBody Booking booking, @RequestParam("noOfTickets")int noOfTickets){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        Booking b = booking;
        b.setBookingNo(RandomStringUtils.randomAlphabetic(4));
        b.setCreateDate(new Date());
        b = service.save(booking);

        if(noOfTickets >0) {
            List<Ticket> tickets = generateTicket(noOfTickets, b);
            service.saveTickets(tickets);
        }

        if(b == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(b);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable(name = "id")Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        Booking booking = service.get(id);
        if(booking == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(booking);
    }

    @PostMapping(value = "/search", params = "bookingNo")
    public ResponseEntity<List<Booking>> searchBookings(@RequestParam(name="bookingNo")String bookingNo){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        if(StringUtils.isNoneEmpty(bookingNo))
            return ResponseEntity.ok().headers(responseHeaders).body(service.searchByBookingNo(bookingNo));
        else
            return ResponseEntity.notFound().headers(responseHeaders).build();
    }

    @PostMapping(value = "/search", params = "username")
    public ResponseEntity<List<Booking>> searchBookingsByUsername(@RequestParam(name="username")String username){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        if(StringUtils.isNoneEmpty(username))
            return ResponseEntity.ok().headers(responseHeaders).body(service.searchByUsername(username));
        else
            return ResponseEntity.notFound().headers(responseHeaders).build();
    }

    private List<Ticket> generateTicket(int noOfTicket, Booking booking){
        List<Ticket> tickets = new ArrayList<>();

        Ticket t = null;
        for(int i=0; i<noOfTicket; i++){
            t = new Ticket();
            t.setBooking(booking);
            t.setSeatNo(RandomStringUtils.randomNumeric(2));
            tickets.add(t);
        }
        return tickets;
    }

}
