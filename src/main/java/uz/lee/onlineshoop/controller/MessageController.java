package uz.lee.onlineshoop.controller;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.MessageDto;
import uz.lee.onlineshoop.entity.Message;
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
    @SneakyThrows
    @GetMapping()
    @Cacheable("messages")
    public ResponseEntity<?> getAll() {
        Thread.sleep(5000);
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Message message) throws URISyntaxException {
        MessageDto saved = messageService.saveMessage(message);
        if(saved == null) {
            return ResponseEntity.status(500).body("Something is null!");
        }
        URI uri = new URI("/api/chat/create" + message.getId());
        MessageDto messageDto = new MessageDto();
        return ResponseEntity.created(uri).body("Chat created!");
    }
}