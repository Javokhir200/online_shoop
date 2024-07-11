package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.AddressDto;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.OrderDto;
import uz.lee.onlineshoop.entity.AddressEntity;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.repository.*;
import uz.lee.onlineshoop.entity.OrderEntity;
import uz.lee.onlineshoop.util.StatusConstants;

@Service
public class OrderService {

    private final AddressEntityRepository addressEntityRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserEntityRepository userEntityRepository;
    private final StatusRepository statusRepository;

    public OrderService(AddressEntityRepository addressEntityRepository,
                        PaymentMethodRepository paymentMethodRepository,
                        UserEntityRepository userEntityRepository,
                        StatusRepository statusRepository) {
        this.addressEntityRepository = addressEntityRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userEntityRepository = userEntityRepository;
        this.statusRepository = statusRepository;
    }


    public ApiResponse addOrder(UserEntity user, OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();

        AddressDto address = orderDto.getAddress();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(address.getStreet());
        addressEntity.setHome(address.getHome());
        addressEntity.setCity(address.getCity());
        addressEntityRepository.save(addressEntity);

        orderEntity.setAddress(addressEntity);
        orderEntity.setProduct(orderEntity.getProduct());
        orderEntity.setPaymentMethod(paymentMethodRepository.getReferenceById(orderDto.getPaymentMethodId()));
        orderEntity.setQuantity(orderDto.getQuantity());
        orderEntity.setTotalPrice(orderDto.getTotalPrice());
        orderEntity.setUser(userEntityRepository.getReferenceById(user.getId()));
        orderEntity.setStatus(statusRepository.findByName(StatusConstants.ORDERED));

        return new ApiResponse(true,"Order saved successfully !!!");
    }
}
