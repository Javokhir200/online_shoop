package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}