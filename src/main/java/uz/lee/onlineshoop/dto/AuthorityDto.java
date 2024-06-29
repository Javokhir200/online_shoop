package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorityDto {
    private Long id;
    private String name;
    private RoleDto role;
    private String description;
}
