package uz.lee.onlineshoop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lee.onlineshoop.dto.AttachmentDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditByUserDto {
    private String username;

    private String fullName;

    private String password;

    private Integer genderId;
    private AttachmentDto attachment;

    private String cardNumber;

    private String phoneNumber;
}
