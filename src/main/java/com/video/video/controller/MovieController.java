package com.video.video.controller;

import com.video.video.model.Movie;
import com.video.video.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @PostConstruct
    public void saveOnStart() {
        movieRepository.save(Movie.builder()
                .name("Terminator: Mroczne przeznaczenie")
                .price(100L)
                .build());
        movieRepository.save(Movie.builder()
                .name("The Game Changers")
                .price(30L)
                .build());
        movieRepository.save(Movie.builder()
                .name("Dorwać Gunthera")
                .price(500L)
                .build());
    }

    @PostMapping
    public void save(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

}
