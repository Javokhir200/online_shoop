package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.Message;
import uz.lee.onlineshoop.repository.MessageRepository;
import uz.lee.onlineshoop.service.MessageService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Message message) throws URISyntaxException {
        Message saved = messageService.saveMessage(message);
        if(saved == null) {
            return ResponseEntity.status(500).body("Something is null!");
        }
        URI uri = new URI("/api/chat/create" + message.getId());
        return ResponseEntity.created(uri).body("Chat created!");
    }
}
