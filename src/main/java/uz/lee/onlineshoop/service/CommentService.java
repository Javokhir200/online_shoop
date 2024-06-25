package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.entity.Comment;
import uz.lee.onlineshoop.repository.CommentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    public Comment save(Comment comment) {
        if(comment == null) {
            return null;
        }
        return commentRepository.save(comment);
    }
    public Optional<Comment> getById(Long id) {
        if(!commentRepository.existsById(id)) {
            return Optional.empty();
        }
        return commentRepository.findById(id);
    }
}
