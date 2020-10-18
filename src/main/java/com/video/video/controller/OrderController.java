package com.video.video.controller;

import com.video.video.model.Movie;
import com.video.video.model.Order;
import com.video.video.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Michał
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void save(@RequestBody Order order) {
        List<Movie> movies = order.getMovies();
        movies.forEach(movie -> {
            if (movie.getName().equals("Terminator: Mroczne przeznaczenie")) {
                movie.setPrice(100L);
            }
            if (movie.getName().equals("The Game Changers")) {
                movie.setPrice(30L);
            }
            if (movie.getName().equals("Dorwać Gunthera")) {
                movie.setPrice(500L);
            }
        });
        orderService.save(order);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

}
