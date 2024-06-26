package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttachmentDto {
    private Long id;
    private String originalName;
    private String description;
    private String submittedName;
    private String fileUrl;
}
