package uz.lee.onlineshoop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String name;
    private int typeId;
    private Double price;
    private int storeId;
    private String description;
    private MultipartFile[] images;
}
