package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
