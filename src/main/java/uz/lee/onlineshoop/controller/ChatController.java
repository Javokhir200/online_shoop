package uz.lee.onlineshoop.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.entity.Chat;
import uz.lee.onlineshoop.repository.ChatRepository;
import uz.lee.onlineshoop.service.ChatService;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatRepository chatRepository;
    private final ChatService chatService;
    public ChatController(ChatRepository chatRepository, ChatService chatService) {
        this.chatRepository = chatRepository;
        this.chatService = chatService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Chat chat) throws URISyntaxException {
        chat.setCreatedAt(LocalDateTime.now());
        Chat savedChat = chatService.saveChat(chat);
        URI uri = new URI("/api/chat/create" + savedChat.getId());
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/get{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!chatRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Not found with id - " + id);
        }
        return ResponseEntity.ok(chatRepository.findById(id));
    }
}
