package com.video.video.controller;

import com.video.video.model.Movie;
import com.video.video.model.Order;
import com.video.video.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Michał
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public void save(@RequestBody Order order) {
        Movie movie = order.getMovie();
        if (movie.getName().equals("Terminator: Mroczne przeznaczenie")) {
            movie.setPrice("100");
        }
        if (movie.getName().equals("The Game Changers")) {
            movie.setPrice("30");
        }
        if (movie.getName().equals("Dorwać Gunthera")) {
            movie.setPrice("500");
        }
        orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        Optional<Order> order2 = orderRepository.findById(id);
        return order2.get();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
