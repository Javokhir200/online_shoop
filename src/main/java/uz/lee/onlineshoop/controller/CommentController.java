package uz.lee.onlineshoop.controller;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.Comment;
import uz.lee.onlineshoop.repository.CommentRepository;
import uz.lee.onlineshoop.service.CommentService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentRepository repository;
    private final CommentService service;

    public CommentController(CommentRepository repository, CommentService service) {
        this.repository = repository;
        this.service = service;
    }
    @SneakyThrows
    @GetMapping
    @Cacheable("comments")
    public ResponseEntity<List<Comment>> getAll() {
        Thread.sleep(5000);
        return ResponseEntity.ok(service.getAllComments());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CommentDto comment) throws URISyntaxException {
        if(comment == null) {
            return ResponseEntity.status(400).body("Something went wrong!");
        }
        URI uri = new URI("/api/comments");
        CommentDto commentDto = service.save(comment);
        return ResponseEntity.created(uri).body(commentDto);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    @Cacheable(value = "comments",key = "#id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.status(400).body("Comment not found!");
        }
        Thread.sleep(5000);
        CommentDto commentDto = service.getById(id);
        return ResponseEntity.ok(commentDto);
    }
}
