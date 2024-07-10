package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import uz.lee.onlineshoop.entity.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntitiesByVerificationCode(String verificationCode);

    Optional<UserDetails> findByUsername(String username);

    Boolean existsByUsername(String username);
}