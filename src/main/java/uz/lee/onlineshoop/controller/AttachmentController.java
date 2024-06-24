package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.Attachment;
import uz.lee.onlineshoop.repository.AttachmentRepository;
import uz.lee.onlineshoop.service.AttachmentService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
    private final AttachmentRepository repository;
    private final AttachmentService service;

    public AttachmentController(AttachmentRepository repository, AttachmentService service) {
        this.repository = repository;
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Attachment>> getAll() {
        return ResponseEntity.ok(service.getAllAttachment());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Attachment attachment) throws URISyntaxException {
        if(attachment == null) {
            return ResponseEntity.status(400).body("Something went wrong!");
        }
        URI uri = new URI("/api/attachments/" + attachment.getId());
        Attachment save = repository.save(attachment);
        return ResponseEntity.created(uri).body(save);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }
}
