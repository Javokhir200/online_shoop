package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
