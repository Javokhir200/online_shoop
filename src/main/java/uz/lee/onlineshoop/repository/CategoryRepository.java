package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}