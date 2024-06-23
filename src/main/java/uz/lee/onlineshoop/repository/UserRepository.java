package uz.lee.onlineshoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.lee.onlineshoop.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query(value = "select u from UserEntity u where u.full_name = :fullName and u.password = :password")
    UserEntity findByFull_nameAndPassword(@Param("fullName") String fullName,
                                          @Param("password") String password);
}
