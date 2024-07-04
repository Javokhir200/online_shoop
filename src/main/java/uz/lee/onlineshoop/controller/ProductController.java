package uz.lee.onlineshoop.controller;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.ProductDto;
import uz.lee.onlineshoop.entity.ProductEntity;
import uz.lee.onlineshoop.repository.ProductRepository;
import uz.lee.onlineshoop.service.ProductService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService service;
    public ProductController(ProductRepository productRepository, ProductService service) {
        this.productRepository = productRepository;
        this.service = service;
    }
    @SneakyThrows
    @GetMapping
    @Cacheable("products")
    public ResponseEntity<List<ProductEntity>> getAll() {
        Thread.sleep(5000);
        return ResponseEntity.ok(productRepository.findAll());
    }
    @SneakyThrows
    @GetMapping("/{id}")
    @Cacheable(value = "products", key = "#id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Thread.sleep(5000);
        if(service.getByID(id) == null) {
            return ResponseEntity.status(400).body("Product not found with id " + id);
        }
        return ResponseEntity.ok(service.getByID(id));
    }
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ProductDto dto) throws URISyntaxException {
        if(service.save(dto) == null)
            return ResponseEntity.status(400).body("Something is null!");
        URI uri = new URI("/api/products/" + dto.getId());
        return ResponseEntity.created(uri).body(service.save(dto));
    }
    @SneakyThrows
    @DeleteMapping("/{id}")
    @CacheEvict(value = "products", key = "#id")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        if(!productRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Not found product with id - " + id);
        }
        Thread.sleep(5000);
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
