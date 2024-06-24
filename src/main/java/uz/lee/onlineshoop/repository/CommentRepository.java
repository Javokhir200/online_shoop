package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    boolean existsById(Long id);
}
