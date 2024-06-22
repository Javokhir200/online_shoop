package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.lee.onlineshoop.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping()
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}
