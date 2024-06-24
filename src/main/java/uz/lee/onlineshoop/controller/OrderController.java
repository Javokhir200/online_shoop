package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderEntity order) throws URISyntaxException {
        if(order == null) {
            return ResponseEntity.status(400).body("Something is null!");
        }
        URI uri = new URI("/api/chat/create");
        orderService.saveOrder(order);
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if(!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderService.getById(id));
    }
}
