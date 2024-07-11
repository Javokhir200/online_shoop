package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByName(String name);
}