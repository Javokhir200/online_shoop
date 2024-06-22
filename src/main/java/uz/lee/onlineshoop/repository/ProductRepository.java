package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    ProductEntity getById(Long id);
}
