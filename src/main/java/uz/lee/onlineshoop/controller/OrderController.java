package uz.lee.onlineshoop.controller;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.OrderDto;
import uz.lee.onlineshoop.entity.OrderEntity;
import uz.lee.onlineshoop.repository.OrderRepository;
import uz.lee.onlineshoop.service.OrderService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }
    @SneakyThrows
    @GetMapping
    @Cacheable("orders")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        Thread.sleep(5000);
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderDto order) throws URISyntaxException {
        if(order == null) {
            return ResponseEntity.status(400).body("Something went wrong!");
        }
        URI uri = new URI("/api/orders");
        OrderDto orderDto = orderService.saveOrder(order);
        return ResponseEntity.created(uri).body(orderDto);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    @Cacheable(value = "orders",key = "#id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if(!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderService.getById(id));
    }
}
