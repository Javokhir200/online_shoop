package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lee.onlineshoop.dto.product.ProductDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductAttachmentDto {
    private Long id;
    private ProductDto product;
    private AttachmentDto attachment;
}
