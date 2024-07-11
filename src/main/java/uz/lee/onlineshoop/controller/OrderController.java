package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.OrderDto;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@AuthenticationPrincipal UserEntity user, @RequestBody OrderDto orderDto){
        ApiResponse resp = orderService.addOrder(user,orderDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
}
