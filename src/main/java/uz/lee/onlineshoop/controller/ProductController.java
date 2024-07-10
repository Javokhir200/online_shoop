package uz.lee.onlineshoop.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.product.EditProductDto;
import uz.lee.onlineshoop.dto.product.ProductDto;
import uz.lee.onlineshoop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
        ApiResponse apiResponse = productService.getProducts(page,size);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        ApiResponse apiResponse = productService.getProductById(id);
        System.out.println(apiResponse);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductDto productDto) {
        ApiResponse resp = productService.saveProduct(productDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByName(@PathVariable("id") long id) {
        ApiResponse resp = productService.deleteProductById(id);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@RequestBody EditProductDto editProductDto, @PathVariable Long id){
        ApiResponse resp = productService.editProduct(id,editProductDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
}
