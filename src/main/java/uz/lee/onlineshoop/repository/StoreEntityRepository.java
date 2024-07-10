package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.StoreEntity;

public interface StoreEntityRepository extends JpaRepository<StoreEntity, Integer> {
}