package com.cinema.movie.movies.service;

import com.cinema.movie.movies.model.Movie;
import com.cinema.movie.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;

    public List<Movie> listAll(){
        return repo.findAll();
    }
    public Movie save(Movie movie){
        return repo.save(movie);
    }
    public Movie get(long id){
        return repo.getOne(id);
    }
    public void  delete(long id){
        repo.deleteById(id);
    }

    public List<Movie> searchByTitle(String title){
        return repo.findByTitleContaining(title);
    }
}
