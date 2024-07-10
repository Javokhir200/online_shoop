package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.service.CommentService;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController( CommentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = service.save(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable("productId") Long productId) {
        ApiResponse apiResponse = service.getByProductId(productId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
}
