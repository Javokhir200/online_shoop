package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.Category;
import uz.lee.onlineshoop.entity.Comment;
import uz.lee.onlineshoop.entity.ProductEntity;
import uz.lee.onlineshoop.repository.CommentRepository;
import uz.lee.onlineshoop.repository.ProductRepository;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public CommentService(CommentRepository commentRepository,
                          ProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
    }

    public ApiResponse save(CommentDto commentDto) {
        Comment comment = new Comment();
        Optional<ProductEntity> product = productRepository.findById(commentDto.getProductId());

        product.ifPresent(comment::setProduct);
        comment.setText(commentDto.getText());
        comment.setStarsCount(commentDto.getStarsCount());

        commentRepository.save(comment);

        return new ApiResponse(true,"Comment saved successfully");
    }

    public ApiResponse getByProductId(Long productId) {
        Optional<?> comments = commentRepository.getCommentsByProductId(productId);

        return comments.map(
                o -> new ApiResponse(true, "List of Comments", o))
                .orElseGet(() -> new ApiResponse(false, "No comments"));
    }

    public ApiResponse updateComment(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            return new ApiResponse(false, "Comment not found");
        }
        Comment existingComment = optionalComment.get();

        existingComment.setText(commentDto.getText());
        existingComment.setStarsCount(commentDto.getStarsCount());

        commentRepository.save(existingComment);
        return new ApiResponse(true, "Comment updated successfully");
    }

    public ApiResponse deleteComment(Long id) {
        commentRepository.deleteById(id);
        return new ApiResponse(true, "Comment deleted successfully");
    }
}
