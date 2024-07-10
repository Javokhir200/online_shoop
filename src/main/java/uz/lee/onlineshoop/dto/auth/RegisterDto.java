package uz.lee.onlineshoop.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String username;
    private String fullName;
    private String email;
    private Integer genderId;
    private String password;
    private String phoneNumber;
}
