package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
}
