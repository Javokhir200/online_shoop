package uz.lee.onlineshoop.controller;

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
        AttachmentDto dto = service.saveAttachment(attachment);
        return ResponseEntity.created(uri).body(dto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
