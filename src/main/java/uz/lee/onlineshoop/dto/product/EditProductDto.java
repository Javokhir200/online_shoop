package uz.lee.onlineshoop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditProductDto {
    private String name;
    private int typeId;
    private Double price;
    private String description;
}
