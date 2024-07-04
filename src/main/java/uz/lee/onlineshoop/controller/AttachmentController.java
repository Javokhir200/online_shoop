package uz.lee.onlineshoop.controller;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.AttachmentDto;
import uz.lee.onlineshoop.entity.Attachment;
import uz.lee.onlineshoop.service.AttachmentService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
    private final AttachmentService service;

    public AttachmentController(AttachmentService service) {
        this.service = service;
    }
    @SneakyThrows
    @GetMapping
    @Cacheable("attachments")
    public ResponseEntity<List<Attachment>> getAll() {
        Thread.sleep(5000);
        return ResponseEntity.ok(service.getAllAttachment());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AttachmentDto attachment) throws URISyntaxException {
        if(attachment == null) {
            return ResponseEntity.status(400).body("Something went wrong!");
        }
        URI uri = new URI("/api/attachments/" + attachment.getId());
        AttachmentDto dto = service.saveAttachment(attachment);
        return ResponseEntity.created(uri).body(dto);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    @Cacheable(value = "attachments",key = "#id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
