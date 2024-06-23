package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity authenticate(String fullName, String password) {
        UserEntity user = userRepository.findByFull_nameAndPassword(fullName, password);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
