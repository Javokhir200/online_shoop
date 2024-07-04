package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.*;
import uz.lee.onlineshoop.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
    /**
     * orderEntity kirib keganda uni orderDtoga o'girib save qiladi
     * @author Aziz

     * @since 2024-07-04
     */
    public OrderDto saveOrder(OrderDto order) {
        if(order.getUser() == null ||
        order.getAddress() == null ||
        order.getTotalPrice() == null ||
        order.getProduct() == null ||
        order.getQuantity() == null ||
        order.getOrderAt() == null) {
            return null;
        }
        OrderEntity entity = new OrderEntity(order.getId(),
                getUser(order),
                getProduct(order),
                order.getOrderAt(),
                order.getExpireAt(),
                getAddress(order),
                order.getQuantity(),
                getStatus(order),
                order.getTotalPrice(),
                getPaymentMethod(order));
        orderRepository.save(entity);
        return order;
    }
    /**
     * orderEntity null bo'lmasa uni orderDtoga o'tqizib return qiladi.
     * Unga yana bir nechta dtolar kerak bo'ladi, pasda yozib qo'yilgan methoddi yonida.
     * @return OrderDto obyekti
     * @author Aziz
     * @since 2024-07-04
     */
    public OrderDto getById(Long id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        if(order == null) {
            return null;
        }
        ProductDto productDto = getProductDto(order);
        AddressDto addressDto = getAddressDto(order);
        StatusDto statusDto = getStatusDto(order);
        PaymentMethodDto paymentMethodDto = getPaymentMethodDto(order);
        UserDto userDto = getUserDto(order);
        return new OrderDto(order.getId(),
                userDto,
                productDto,
                order.getOrderAt(),
                order.getExpireAt(),
                addressDto,
                order.getQuantity(),
                statusDto,
                order.getTotalPrice(),
                paymentMethodDto);
    }
    private static UserEntity getUser(OrderDto order) {
        return new UserEntity(
                order.getUser().getId(),
                order.getUser().getCreateAt(),
                getRole(order),
                order.getUser().getFullName(),
                order.getUser().getEmail(),
                order.getUser().getPassword(),
                order.getUser().getSentCode(),
                order.getUser().getGender(),
                getAttachment(order),
                order.getUser().getCardNumber(),
                order.getUser().getPhoneNumber()
        );
    }

    /**
     *user needs role
     */
    private static RoleEntity getRole(OrderDto order) {
        return new RoleEntity(
                order.getUser().getRole().getId(),
                order.getUser().getRole().getName(),
                order.getUser().getRole().getDescription()
        );
    }

    /**
     * user needs attachment
     */
    private static Attachment getAttachment(OrderDto order) {
        return new Attachment(
                order.getUser().getAttachment().getId(),
                order.getUser().getAttachment().getOriginalName(),
                order.getUser().getAttachment().getDescription(),
                order.getUser().getAttachment().getSubmittedName(),
                order.getUser().getAttachment().getFileUrl()
        );
    }

    /**
     * OrderEntity needs ProductEntity
     */
    private static ProductEntity getProduct(OrderDto order) {
        return new ProductEntity(
                order.getProduct().getId(),
                order.getProduct().getName(),
                getType(order),
                order.getProduct().getPrice(),
                order.getProduct().getCount(),
                getStore(order),
                order.getProduct().getDescription()
        );
    }

    /**
     * productEntity needs type
     * @author Aziz
     *
     */
    private static Type getType(OrderDto order) {
        return new Type(
                order.getProduct().getType().getId(),
                order.getProduct().getType().getName(),
                order.getProduct().getType().getDescription(),
                new Category()
        );
    }

    /**
     * productEntity needs StoreEntity
     */
    private static StoreEntity getStore(OrderDto order) {
        return new StoreEntity(
          order.getProduct().getStore().getId(),
                order.getProduct().getType().getName(),
                order.getProduct().getType().getDescription(),
                getUser(order),
                LocalDateTime.now()
        );
    }

    /**
     * addressEntity needs orderEntity
     */
    private static AddressEntity getAddress(OrderDto order) {
        return new AddressEntity(
                order.getAddress().getId(),
                order.getAddress().getHome(),
                order.getAddress().getStreet(),
                order.getAddress().getCity()
        );
    }

    /**
     * orderEntity needs status
     */
    private static Status getStatus(OrderDto orderDto) {
        return new Status(
                orderDto.getStatus().getId(),
                orderDto.getStatus().getName()
        );
    }

    /**
     * orderEntity needs paymentMethod
     */
    private static PaymentMethod getPaymentMethod(OrderDto order) {
        return new PaymentMethod(
                order.getPaymentMethod().getId(),
                order.getPaymentMethod().getMethod(),
                order.getPaymentMethod().getDescription()
        );
    }

    /**
     * orderDto needs userDto
     */

    private static UserDto getUserDto(OrderEntity order) {
        return new UserDto(
                order.getUser().getId(),
                order.getUser().getCreated_at(),
                getRoleDto(order),
                order.getUser().getFullName(),
                order.getUser().getEmail(),
                order.getUser().getPassword(),
                order.getUser().getSentCode(),
                order.getUser().getGender(),
                getAttachmentDto(order),
                order.getUser().getCardNumber(),
                order.getUser().getPhoneNumber()
        );
    }

    /**
     * userDto needs roleDto
     */
    private static RoleDto getRoleDto(OrderEntity order) {
        return new RoleDto(
                order.getUser().getRole().getId(),
                order.getUser().getRole().getName(),
                order.getUser().getRole().getDescription()
        );
    }

    /**
     * userDto needs attachmentDto
     */
    private static AttachmentDto getAttachmentDto(OrderEntity order) {
        return new AttachmentDto(
                order.getUser().getAttachment().getId(),
                order.getUser().getAttachment().getOriginalName(),
                order.getUser().getAttachment().getDescription(),
                order.getUser().getAttachment().getSubmittedName(),
                order.getUser().getAttachment().getFileUrl()
        );
    }
    /**
     * orderDto needs productDto
     */
    private static ProductDto getProductDto(OrderEntity order) {
        return new ProductDto(
                order.getProduct().getId(),
                order.getProduct().getName(),
                getTypeDto(order),
                order.getProduct().getPrice(),
                order.getProduct().getCount(),
                getStoreDto(order),
                order.getProduct().getDescription()
        );
    }

    /**
     * productDto needs storeDto
     */
    private static StoreDto getStoreDto(OrderEntity order) {
        return new StoreDto(
                order.getProduct().getStore().getId(),
                order.getProduct().getType().getName(),
                order.getProduct().getType().getDescription(),
                getUserDto(order),
                LocalDateTime.now()
        );
    }

    /**
     * productDto needs TypeDto
     */
    private static TypeDto getTypeDto(OrderEntity order) {
        return new TypeDto(
                order.getProduct().getType().getId(),
                order.getProduct().getType().getName(),
                order.getProduct().getType().getDescription()
        );
    }

    /**
     * orderDto needs statusDto
     */
    private static StatusDto getStatusDto(OrderEntity order) {
        return new StatusDto(
                order.getStatus().getId(),
                order.getStatus().getName()
        );
    }

    /**
     * orderDto needs paymentMethodDto
     */
    private static PaymentMethodDto getPaymentMethodDto(OrderEntity order) {
        return new PaymentMethodDto(
                order.getPaymentMethod().getId(),
                order.getPaymentMethod().getMethod(),
                order.getPaymentMethod().getDescription()
        );
    }

    /**
     * orderDto needs addressDto
     */
    private static AddressDto getAddressDto(OrderEntity order) {
        return new AddressDto(
                order.getAddress().getId(),
                order.getAddress().getHome(),
                order.getAddress().getStreet(),
                order.getAddress().getCity()
        );
    }
}