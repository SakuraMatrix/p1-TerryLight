package com.github.TerryLight.LimonStand.controller;

import com.github.TerryLight.LimonStand.domain.Order;
import com.github.TerryLight.LimonStand.service.OrderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService= orderService;
    }

    @GetMapping("")
    public Flux<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Order> get (@PathVariable("id") int id) {
        return orderService.get(id);
    }

    @PostMapping("")
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }
}