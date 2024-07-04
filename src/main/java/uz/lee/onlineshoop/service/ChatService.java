package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.*;
import uz.lee.onlineshoop.repository.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /**
     * chatDto kirib keladi uni entityga o'girib save qiladi va ozini qaytarvoradi
     */
    public ChatDto saveChat(ChatDto chat) {
        UserEntity user = getUser(chat);
        StoreEntity store = getStore(chat, user);
        Chat chat1 = new Chat(chat.getId(), user, store, chat.getCreatedAt(), chat.getUpdatedAt());
        chatRepository.save(chat1);
        return chat;
    }

    /**
     * id kirib keladi shu idga tekshirib dtoga o'girib qaytaradi
     */
    public ChatDto getById(Long id) {
        Chat chat = chatRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Chat not found!"));
        UserDto userDto = getUserDto(chat);
        StoreDto storeDto = getStoreDto(chat, userDto);
        return new ChatDto(chat.getId(),userDto,storeDto,chat.getCreatedAt(),chat.getUpdatedAt());
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
    private static UserEntity getUser(ChatDto chat) {
        return new UserEntity(
                chat.getUser().getId(),
                chat.getUser().getCreateAt(),
                getRole(chat),
                chat.getUser().getFullName(),
                chat.getUser().getEmail(),
                chat.getUser().getPassword(),
                chat.getUser().getSentCode(),
                chat.getUser().getGender(),
                getAttachment(chat),
                chat.getUser().getCardNumber(),
                chat.getUser().getPhoneNumber()
        );
    }
    private static RoleEntity getRole(ChatDto chat) {
        return new RoleEntity(
                chat.getUser().getRole().getId(),
                chat.getUser().getRole().getName(),
                chat.getUser().getRole().getDescription());
    }
    private static Attachment getAttachment(ChatDto chat) {
        return new Attachment(
                chat.getUser().getAttachment().getId(),
                chat.getUser().getAttachment().getOriginalName(),
                chat.getUser().getAttachment().getDescription(),
                chat.getUser().getAttachment().getSubmittedName(),
                chat.getUser().getAttachment().getFileUrl()
        );
    }
    private static StoreEntity getStore(ChatDto chat, UserEntity user) {
        return new StoreEntity(
                chat.getStore().getId(),
                chat.getStore().getName(),
                chat.getStore().getDescription(),
                user,
                chat.getStore().getCreatedAt());
    }
}
