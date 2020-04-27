package com.cinema.movie.movies.controller;

import com.cinema.movie.movies.model.Movie;
import com.cinema.movie.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/movies", produces = "application/json")
public class MoviesController {
    private static final String HEADER = "Origin, X-Requested-With, Content-Type, Accept";
    private static final String ALLOW_HEADER = "Access-Control-Allow-Headers";

    @Autowired
    MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> movies(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        return ResponseEntity.ok().headers(responseHeaders).body(service.listAll());
    }

    @PutMapping
    public ResponseEntity<Movie> update(@RequestBody Movie movie){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        Movie m = service.save(movie);
        if(m == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(m);
    }

    @PostMapping
    public ResponseEntity<Movie> add(@RequestBody Movie movie){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        Movie m = service.save(movie);
        if(m == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable(name = "id")Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        Movie movie = service.get(id);
        if(movie == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().headers(responseHeaders).body(movie);
    }

    @PostMapping(value = "/search", params = {"title"})
    public ResponseEntity<List<Movie>> searchMovie(@RequestParam("title")String title){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ALLOW_HEADER, HEADER);
        return ResponseEntity.ok().headers(responseHeaders).body(service.searchByTitle(title));
    }

}
