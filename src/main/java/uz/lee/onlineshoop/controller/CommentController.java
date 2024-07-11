package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.service.CommentService;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.save(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable("productId") Long productId) {
        ApiResponse apiResponse = commentService.getByProductId(productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.updateComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        ApiResponse resp = commentService.deleteComment(id);
        return ResponseEntity.status(resp.isSuccess() ? 200 : 400).body(resp);
    }
}
