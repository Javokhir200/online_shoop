package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.entity.OrderEntity;
import uz.lee.onlineshoop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }
    public Optional<OrderEntity> getById(Long id) {
        return orderRepository.findById(id);
    }
}
