package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.ProductEntity;
import uz.lee.onlineshoop.repository.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping()
    public ResponseEntity<List<ProductEntity>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productRepository.getById(id));
    }
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductEntity entity) {
        return ResponseEntity.ok(productRepository.save(entity));
    }
}
