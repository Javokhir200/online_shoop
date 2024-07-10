package uz.lee.onlineshoop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lee.onlineshoop.dto.AttachmentDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private AttachmentDto attachment;
    private String phoneNumber;
}
