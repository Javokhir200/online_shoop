package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.Chat;
import uz.lee.onlineshoop.repository.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    public ChatDto saveChat(Chat chat) {
        UserDto userDto = getUserDto(chat);
        StoreDto storeDto = getStoreDto(chat, userDto);
        chatRepository.save(chat);
        return new ChatDto(chat.getId(),
                userDto,
                storeDto,
                chat.getCreatedAt(),
                null);
    }
    public ChatDto getById(Long id) {
        Chat chat = chatRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Chat not found!"));
        UserDto userDto = getUserDto(chat);
        StoreDto storeDto = getStoreDto(chat, userDto);
        return new ChatDto(chat.getId(),userDto,storeDto,chat.getCreatedAt(),null);
    }
    private static UserDto getUserDto(Chat chat) {
        return new UserDto(
                chat.getUser().getId(),
                chat.getUser().getCreated_at(),
                getRoleDto(chat),
                chat.getUser().getFullName(),
                chat.getUser().getEmail(),
                chat.getUser().getPassword(),
                chat.getUser().getSentCode(),
                chat.getUser().getGender(),
                getAttachmentDto(chat),
                chat.getUser().getCardNumber(),
                chat.getUser().getPhoneNumber()
        );
    }
    private static RoleDto getRoleDto(Chat chat) {
        return new RoleDto(chat.getUser().getRole().getId(),
                chat.getUser().getRole().getName(),
                chat.getUser().getRole().getDescription());
    }
    private static AttachmentDto getAttachmentDto(Chat chat) {
        return new AttachmentDto(
                chat.getUser().getAttachment().getId(),
                chat.getUser().getAttachment().getOriginalName(),
                chat.getUser().getAttachment().getDescription(),
                chat.getUser().getAttachment().getSubmittedName(),
                chat.getUser().getAttachment().getFileUrl()
        );
    }
    private static StoreDto getStoreDto(Chat chat,UserDto userDto) {
        return new StoreDto(
                chat.getStore().getId(),
                chat.getStore().getName(),
                chat.getStore().getDescription(),
                userDto,
                chat.getStore().getCreatedAt());
    }
}
