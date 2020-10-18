package com.video.video.service;

import com.video.video.model.Movie;
import com.video.video.model.Order;
import com.video.video.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order) {
        List<Movie> movies = order.getMovies();
        long sum = movies.stream()
                .mapToLong(Movie::getPrice)
                .sum();
        order.setSubtotal(sum);
        orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
