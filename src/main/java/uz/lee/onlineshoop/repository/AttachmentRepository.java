package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    boolean existsById(Long id);
}
