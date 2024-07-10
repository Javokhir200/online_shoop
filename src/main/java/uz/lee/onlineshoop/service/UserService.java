package uz.lee.onlineshoop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.user.EditByUserDto;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.exception.DuplicateResourceException;
import uz.lee.onlineshoop.repository.UserEntityRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserEntityRepository userRepository;

    public UserService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ApiResponse editByUser(Long userId,EditByUserDto userDto) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()){
            Boolean exist = userRepository.existsByUsername(userDto.getUsername());
            if (exist){
                throw new DuplicateResourceException("This username is already exists !!!");
            }
            UserEntity user = userOpt.get();
            if (userDto.getUsername()!=null)
                user.setUsername(userDto.getUsername());
            if (userDto.getPassword()!=null)
                user.setPassword(userDto.getPassword());
            if (userDto.getPhoneNumber()!=null)
                user.setPhoneNumber(userDto.getPhoneNumber());
            if (userDto.getFullName()!=null)
                user.setFullName(userDto.getFullName());
            if (userDto.getUsername()!=null)
                user.setUsername(userDto.getUsername());
            if (userDto.getCardNumber()!=null)
                user.setCardNumber(userDto.getCardNumber());

            userRepository.save(user);
            return new ApiResponse(true,"User saved successfully !!!");
        }
        return new ApiResponse(false,"User can't be found");
    }
}
