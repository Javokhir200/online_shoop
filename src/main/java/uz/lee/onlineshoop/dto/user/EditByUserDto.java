package uz.lee.onlineshoop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditByUserDto {
    private String username;

    private String fullName;

    private String password;

    private Integer genderId;

    private String cardNumber;

    private String phoneNumber;
}
