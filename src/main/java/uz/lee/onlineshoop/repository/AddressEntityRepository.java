package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.AddressEntity;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
}