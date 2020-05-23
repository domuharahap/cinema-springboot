package com.cinema.bookings.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;
    private Long showtimeId;
    private String bookingNo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "booking")
    private List<Ticket> tickets;
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Booking(){}

    public Booking(Long id, Long movieId, Long showtimeId, String bookingNo, List<Ticket> tickets, String username, Date createDate) {
        this.id = id;
        this.movieId = movieId;
        this.showtimeId = showtimeId;
        this.bookingNo = bookingNo;
        this.tickets = tickets;
        this.username = username;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", showtimeId=" + showtimeId +
                ", bookingNo='" + bookingNo + '\'' +
                ", tickets=" + tickets +
                ", username='" + username + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
