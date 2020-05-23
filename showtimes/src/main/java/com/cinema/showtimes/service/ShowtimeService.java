package com.cinema.showtimes.service;

import com.cinema.showtimes.domain.Showtime;
import com.cinema.showtimes.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository repository;


    public Showtime get(Long id) {
        return repository.getOne(id);
    }

    public Showtime save(Showtime showtime) {
        return repository.save(showtime);
    }

    public List<Showtime> listAll() {
        return repository.findAll();
    }

    public List<Showtime> getByMovieId(Long id) {
        return repository.findByMovieId(id);
    }

    public List<Showtime> getByMovieIdAndStartTimeGtNow(Long movieId, Date nowTime) {
        return repository.findByMovieIdAndStartTimeGreaterThan(movieId, nowTime);
    }

    public List<Showtime> getByStartTimeGtNow(Date dateTime) {
        return repository.findByStartTimeGreaterThan(dateTime);
    }

    public List<Showtime> getByMovieIdAndStartTimeBetween(Long movieId, Date date, Date midnightNextDay) {
        return repository.findByMovieIdAndStartTimeBetween(movieId, date, midnightNextDay);
    }
}
