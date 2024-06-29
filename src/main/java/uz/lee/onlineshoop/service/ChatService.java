package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.entity.Chat;
import uz.lee.onlineshoop.repository.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
