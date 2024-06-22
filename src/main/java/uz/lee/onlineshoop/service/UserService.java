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

    public UserEntity register(UserEntity user) throws Exception {
        // Ro'yxatdan o'tkazish logikasi (masalan, foydalanuvchini tekshirish va saqlash)
        if (userRepository.findByFullName(user.getFull_name()) != null) {
            throw new Exception("User already exists");
        }
        return userRepository.save(user);
    }

    public UserEntity authenticate(String fullName, String password) {
        return userRepository.findByFullNameAndPassword(fullName, password);
    }
}
