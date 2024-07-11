package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender,Integer> {
}
