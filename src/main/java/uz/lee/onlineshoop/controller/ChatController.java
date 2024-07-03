package uz.lee.onlineshoop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.onlineshoop.dto.*;
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
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Chat chat) throws URISyntaxException {
        chat.setCreatedAt(LocalDateTime.now());
        ChatDto savedChat = chatService.saveChat(chat);
        URI uri = new URI("/api/chat/" + savedChat.getId());
        return ResponseEntity.created(uri).body(savedChat);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!chatRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Not found with id - " + id);
        }
        ChatDto chatDto = chatService.getById(id);
        return ResponseEntity.ok(chatDto);
    }

}
