package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.AttachmentDto;
import uz.lee.onlineshoop.dto.RoleDto;
import uz.lee.onlineshoop.dto.UserDto;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity authenticate(String fullName, String password) {
        UserEntity user = userRepository.findByFullNameAndPassword(fullName, password);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public UserDto save(UserEntity user) {
        UserEntity entity = userRepository.save(user);
        AttachmentDto dto = new AttachmentDto(
                entity.getAttachment().getId(),
                entity.getAttachment().getOriginalName(),
                entity.getAttachment().getDescription(),
                entity.getAttachment().getSubmittedName(),
                entity.getAttachment().getFileUrl()
        );
        return getUserDto(entity,dto);
    }
    private static UserDto getUserDto(UserEntity entity, AttachmentDto dto) {
        RoleDto roleDto = new RoleDto(entity.getRole().getId(),
                entity.getRole().getName(),
                entity.getRole().getDescription());

        return new UserDto(
                entity.getId(),
                entity.getCreated_at(),
                roleDto,
                entity.getFullName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getSentCode(),
                entity.getGender(),
                dto,
                entity.getCardNumber(),
                entity.getPhoneNumber()
        );
    }
}
