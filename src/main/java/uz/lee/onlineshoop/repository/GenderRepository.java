package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.GenderEntity;

public interface GenderRepository extends JpaRepository<GenderEntity,Integer> {
}
