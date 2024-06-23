package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductService productService;
    public ProductService(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

}
