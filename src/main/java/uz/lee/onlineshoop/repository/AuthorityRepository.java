package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.onlineshoop.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}