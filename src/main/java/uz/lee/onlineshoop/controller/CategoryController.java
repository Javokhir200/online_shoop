package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.CategoryDto;
import uz.lee.onlineshoop.service.CategoryService;

@RestController
@RequestMapping("/api/product/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse resp = categoryService.getAll();;
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse resp = categoryService.getAll();
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer id){
        ApiResponse resp = categoryService.getAll();
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> addCategory(@PathVariable Integer id){
        ApiResponse resp = categoryService.getAll();
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
}
