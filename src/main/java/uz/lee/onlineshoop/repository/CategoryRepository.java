package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}