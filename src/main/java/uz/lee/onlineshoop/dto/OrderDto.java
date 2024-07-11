package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lee.onlineshoop.dto.product.ProductDto;
import uz.lee.onlineshoop.dto.user.UserDto;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long productId;
    private AddressDto address;
    private Integer quantity;
    private String totalPrice;
    private Long paymentMethodId;
}
