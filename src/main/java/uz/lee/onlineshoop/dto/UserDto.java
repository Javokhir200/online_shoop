package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private LocalDateTime createAt;
    private RoleDto role;
    private String fullName;
    private String email;
    private String password;
    private String sentCode;
    private String gender;
    private AttachmentDto attachment;
    private String cardNumber;
    private String phoneNumber;
}
