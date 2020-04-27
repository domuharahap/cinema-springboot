package com.cinema.showtimes.repository;

import com.cinema.showtimes.domain.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {

    List<Showtime> findByMovieId(Long id);

    //@Query("SELECT s from Showtime s WHERE u.movieId =?1 AND u.startTime > ?2")
    List<Showtime> findByMovieIdAndStartTimeGreaterThan(Long movieId, Date nowTime);

    List<Showtime> findByStartTimeGreaterThan(Date dateTime);

    List<Showtime> findByMovieIdAndStartTimeBetween(Long movieId, Date date, Date midnightNextDay);
}
