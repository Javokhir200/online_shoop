package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByFullName(String fullName);
    UserEntity findByFullNameAndPassword(String fullName, String password);
}
