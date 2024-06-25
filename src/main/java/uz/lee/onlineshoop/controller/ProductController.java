package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.ProductEntity;
import uz.lee.onlineshoop.repository.ProductRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ProductEntity entity) throws URISyntaxException {
        if(entity == null) {
            return ResponseEntity.status(500).body("Something is null!");
        }
        ProductEntity product = productRepository.save(entity);
        URI uri = new URI("/api/products/" + product.getId());
        return ResponseEntity.created(uri).body(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByName(@PathVariable("id") Long id) {
        if(!productRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Not found product with id - " + id);
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
