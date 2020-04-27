package com.cinema.showtimes.controller;

import com.cinema.showtimes.domain.Showtime;
import com.cinema.showtimes.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/showtime", produces = "application/json")
public class ShowtimeController {
    private static final String HEADER = "Origin, X-Requested-With, Content-Type, Accept";
    private static final String ALLOW_HEADER = "Access-Control-Allow-Headers";

    @Autowired
    ShowtimeService service;

    @GetMapping
    public ResponseEntity<List<Showtime>> showtimes(){

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        return ResponseEntity.ok().headers(responseHeaders).body(service.listAll());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Showtime> update(@RequestBody Showtime showtime){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        Showtime s = service.save(showtime);
        if(s == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(s);
    }

    @PostMapping
    public ResponseEntity<Showtime> add(@RequestBody Showtime showtime){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        Showtime s = service.save(showtime);
        if(s == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(s);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtime(@PathVariable(name = "id")Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        Showtime showtime = service.get(id);
        if(showtime == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(showtime);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<List<Showtime>> getShowtimeByMovie(@PathVariable(name = "id")Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        Date nowTime = new Date();
        List<Showtime> showtime = service.getByMovieIdAndStartTimeGtNow(id, nowTime);
        if(showtime == null)
            return ResponseEntity.notFound().headers(responseHeaders).build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(showtime);
    }

    @PostMapping(value="/search",  params = {"movieId", "date"})
    public ResponseEntity<List<Showtime>> search(@RequestParam("movieId")Long movieId, @RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);

        List<Showtime> showtime = new ArrayList<>();
        if(movieId == null && date == null){
            date = new Date();
            date.setTime(new Date().getTime());
            showtime = service.getByStartTimeGtNow(date);
        }else if(movieId == null && date != null) {
            showtime = service.getByStartTimeGtNow(date);
        }else if(movieId != null && date == null) {
            date = new Date();
            showtime = service.getByMovieIdAndStartTimeGtNow(movieId, date);
        }else {
            showtime = service.getByMovieIdAndStartTimeBetween(movieId, date, getMidnightNextDay(date));
        }
        return ResponseEntity.ok().headers(responseHeaders).body(showtime);
    }

    private Date getMidnightNextDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);

        System.out.println(cal.getTime());
        return cal.getTime();
    }
}
