package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lee.onlineshoop.dto.user.UserDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private Long id;
    private UserDto user;
    private LocalDateTime createdAt;
    private boolean isRead;
    private String text;
}
