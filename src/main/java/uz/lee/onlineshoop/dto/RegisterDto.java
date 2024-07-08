package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
}
