package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private UserDto user;
    private ProductDto product;
    private String orderAt;
    private String expireAt;
    private AddressDto address;
    private Integer quantity;
    private StatusDto status;
    private String totalPrice;
    private PaymentMethodDto paymentMethod;
}
