package uz.lee.onlineshoop.controller;

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
    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {
        return ResponseEntity.ok(service.getAllComments());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Comment comment) throws URISyntaxException {
        if(comment == null) {
            return ResponseEntity.status(400).body("Something went wrong!");
        }
        URI uri = new URI("/api/comments");
        CommentDto commentDto = service.save(comment);
        return ResponseEntity.created(uri).body(commentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.status(400).body("Comment not found!");
        }
        CommentDto commentDto = service.getById(id);
        return ResponseEntity.ok(commentDto);
    }
}
