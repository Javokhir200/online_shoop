package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.*;
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

    /**
     * agar commentDtoning texti yoki producti yoki useri
     * null bo'sa null qaytaradi aks holda dtodan entityga
     * o'tqizib save qilib dtoni qaytarib yuvoradi
     */
    public CommentDto save(CommentDto comment) {
        if(comment.getText() == null ||
        comment.getUser() == null ||
        comment.getProduct() == null) {
            return null;
        }
        UserEntity user = getUser(comment);
        ProductEntity product = getProduct(comment);
        Comment comment1 = new Comment(comment.getId(), user, product, comment.getText(), comment.getCreatedAt(), comment.getStarsCount());
        commentRepository.save(comment1);
        return comment;
    }

    /**
     * id orqali commentEntityga o'zgaruvchiga oladi
     * commentni producti yoki useri yoki texti null
     * bo'sa null qaytaradi aks holda dtoga o'girib return qiladi
     */
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found!"));
        if(comment.getText() == null ||
        comment.getUser() == null ||
        comment.getProduct() == null) {
            return null;
        }
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
    private static UserEntity getUser(CommentDto comment) {
        return new UserEntity(
                comment.getUser().getId(),
                comment.getUser().getCreateAt(),
                getRole(comment),
                comment.getUser().getFullName(),
                comment.getUser().getEmail(),
                comment.getUser().getPassword(),
                comment.getUser().getSentCode(),
                comment.getUser().getGender(),
                getAttachment(comment),
                comment.getUser().getCardNumber(),
                comment.getUser().getPhoneNumber()
        );
    }
    private static RoleEntity getRole(CommentDto comment) {
        return new RoleEntity(
                comment.getUser().getRole().getId(),
                comment.getUser().getRole().getName(),
                comment.getUser().getRole().getDescription());
    }
    private static Attachment getAttachment(CommentDto comment) {
        return new Attachment(
                comment.getUser().getAttachment().getId(),
                comment.getUser().getAttachment().getOriginalName(),
                comment.getUser().getAttachment().getDescription(),
                comment.getUser().getAttachment().getSubmittedName(),
                comment.getUser().getAttachment().getFileUrl()
        );
    }
    private static ProductEntity getProduct(CommentDto comment) {
        UserEntity user = getUser(comment);
        return new ProductEntity(
                comment.getProduct().getId(),
                comment.getProduct().getName(),
                getType(comment),
                comment.getProduct().getPrice(),
                comment.getProduct().getCount(),
                getStore(comment,user),
                comment.getProduct().getDescription()
        );
    }
    /**
     * What category should return
     */
    private static Type getType(CommentDto comment) {
        return new Type(
                comment.getProduct().getType().getId(),
                comment.getProduct().getType().getName(),
                comment.getProduct().getType().getDescription(),
                new Category());
    }
    private static StoreEntity getStore(CommentDto comment, UserEntity user) {
        return new StoreEntity(
                comment.getProduct().getStore().getId(),
                comment.getProduct().getStore().getName(),
                comment.getProduct().getStore().getDescription(),
                user,
                comment.getProduct().getStore().getCreatedAt()
        );
    }
}
