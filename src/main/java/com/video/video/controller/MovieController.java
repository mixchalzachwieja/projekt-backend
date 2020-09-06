package com.video.video.controller;

import com.video.video.model.Movie;
import com.video.video.model.Order;
import com.video.video.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void saveOnStart() {
        movieRepository.save(new Movie("Terminator: Mroczne przeznaczenie", "100"));
        movieRepository.save(new Movie("The Game Changers", "30"));
        movieRepository.save(new Movie("Dorwać Gunthera", "500"));
    }

    @PostMapping
    public void save(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.get();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

}
