package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.Message;
import uz.lee.onlineshoop.repository.MessageRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public MessageDto saveMessage(Message message) {
        Message save = messageRepository.save(message);
        AttachmentDto attachmentDto = getAttachmentDto(save);
        UserDto userDto = getUserDto(save);
        ChatDto chatDto = getChatDto(save);
        return new MessageDto(
                save.getId(),
                attachmentDto,
                userDto,
                chatDto,
                save.getCreatedAt(),
                false,
                save.getText()
                );
    }
    private static AttachmentDto getAttachmentDto(Message message) {
        return new AttachmentDto(
                message.getAttachment().getId(),
                message.getAttachment().getOriginalName(),
                message.getAttachment().getDescription(),
                message.getAttachment().getSubmittedName(),
                message.getAttachment().getFileUrl()
        );
    }
    private static UserDto getUserDto(Message message) {
        return new UserDto(
                message.getUser().getId(),
                message.getUser().getCreated_at(),
                getRoleDto(message),
                message.getUser().getFullName(),
                message.getUser().getEmail(),
                message.getUser().getPassword(),
                message.getUser().getSentCode(),
                message.getUser().getGender(),
                getAttachmentDto(message),
                message.getUser().getCardNumber(),
                message.getUser().getPhoneNumber()
        );
    }
    private static RoleDto getRoleDto(Message message) {
        return new RoleDto(message.getUser().getRole().getId(),
                message.getUser().getRole().getName(),
                message.getUser().getRole().getDescription());
    }
    private static ChatDto getChatDto(Message message) {
        return new ChatDto(
                message.getChat().getId(),
                getUserDto(message),
                getStoreDto(getChatDto(message),message),
                LocalDateTime.now(),
                null
        );
    }
    private static StoreDto getStoreDto(ChatDto dto, Message message) {
        return new StoreDto(
                dto.getStore().getId(),
                dto.getStore().getName(),
                dto.getStore().getDescription(),
                getUserDto(message),
                LocalDateTime.now()
        );
    }
}
