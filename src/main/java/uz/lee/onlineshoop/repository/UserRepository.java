package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByFullNameAndPassword(@Param("fullName") String fullName,
                                          @Param("password") String password);
}
