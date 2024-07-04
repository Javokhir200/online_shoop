package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.Comment;
import uz.lee.onlineshoop.repository.CommentRepository;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    public CommentDto save(Comment comment) {
        UserDto userDto = getUserDto(comment);
        CommentDto commentDto = getCommentDto(comment, userDto);
        commentRepository.save(comment);
        return commentDto;
    }
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found!"));
        UserDto userDto = getUserDto(comment);
        return getCommentDto(comment, userDto);
    }
    private static AttachmentDto getAttachmentDto(Comment comment) {
        return new AttachmentDto(
                comment.getUser().getAttachment().getId(),
                comment.getUser().getAttachment().getOriginalName(),
                comment.getUser().getAttachment().getDescription(),
                comment.getUser().getAttachment().getSubmittedName(),
                comment.getUser().getAttachment().getFileUrl()
        );
    }
    private static CommentDto getCommentDto(Comment comment, UserDto userDto) {
        TypeDto typeDto = getTypeDto(comment);
        StoreDto storeDto = getStoreDto(comment,userDto);
        ProductDto productDto = new ProductDto(comment.getProduct().getId(),
                comment.getProduct().getName(),
                typeDto,
                comment.getProduct().getPrice(),
                comment.getStarsCount(),
                storeDto,
                comment.getProduct().getDescription()
        );
        return new CommentDto(comment.getId(),
                userDto,productDto,
                comment.getText(),
                comment.getCreatedAt(),
                comment.getStarsCount());
    }
    private static RoleDto getRoleDto(Comment comment) {
        return new RoleDto(comment.getUser().getRole().getId(),
                comment.getUser().getRole().getName(),
                comment.getUser().getRole().getDescription());
    }
    private static UserDto getUserDto(Comment comment) {
        return new UserDto(
                comment.getUser().getId(),
                comment.getUser().getCreated_at(),
                getRoleDto(comment),
                comment.getUser().getFullName(),
                comment.getUser().getEmail(),
                comment.getUser().getPassword(),
                comment.getUser().getSentCode(),
                comment.getUser().getGender(),
                getAttachmentDto(comment),
                comment.getUser().getCardNumber(),
                comment.getUser().getPhoneNumber()
        );
    }
    private static TypeDto getTypeDto(Comment comment) {
        return new TypeDto(comment.getProduct().getType().getId(),
                comment.getProduct().getType().getName(),
                comment.getProduct().getType().getDescription());
    }
    private static StoreDto getStoreDto(Comment comment, UserDto userDto) {
        return new StoreDto(
                comment.getProduct().getStore().getId(),
                comment.getProduct().getStore().getName(),
                comment.getProduct().getStore().getDescription(),
                userDto,
                comment.getProduct().getStore().getCreatedAt()
        );
    }
}
