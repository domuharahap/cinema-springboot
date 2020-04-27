package com.cinema.bookings.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Ticket(){}

    public Ticket(Long id, String seatNo, Booking booking) {
        this.id = id;
        this.seatNo = seatNo;
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNo='" + seatNo + '\'' +
                ", booking=" + booking +
                '}';
    }
}
