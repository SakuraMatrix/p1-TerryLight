package com.github.TerryLight.LimonStand.service;

import com.github.TerryLight.LimonStand.domain.Order;
import com.github.TerryLight.LimonStand.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Flux<Order> getAll() {return orderRepository.getAll();
    }

    public Mono<Order> get(String id){return orderRepository.get( Integer.parseInt(id));
    }

    public Mono<Order> get(int id) {return orderRepository.get(id); }

    public Order create(Order order) {
        return orderRepository.create(order);
    }
}