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
public class MessageDto {
    private Long id;
    private AttachmentDto attachment;
    private UserDto user;
    private ChatDto chat;
    private LocalDateTime createdAt;
    private boolean isRead;
    private String text;
}
