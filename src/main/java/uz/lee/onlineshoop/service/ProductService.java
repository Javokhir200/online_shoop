package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.*;
import uz.lee.onlineshoop.entity.*;
import uz.lee.onlineshoop.repository.ProductRepository;

import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public ProductDto save(ProductDto dto) {
        Type type = getType(dto);
        StoreEntity store = getStore(dto);
        ProductEntity product = new ProductEntity(dto.getId(),
                dto.getName(),
                type,
                dto.getPrice(),
                dto.getCount(),
                store,
                dto.getDescription());
        repository.save(product);
        return dto;
    }
    public ProductDto getByID(Long id) {
        ProductEntity product = repository.findById(id).orElse(null);
        if(Objects.requireNonNull(product).getStore().getName() == null ||
                Objects.requireNonNull(product).getStore().getUser() == null ||
        product.getPrice() == null ||
        product.getName() == null) {
            return null;
        }
        StoreDto storeDto = getStoreDto(product);
        TypeDto typeDto = getTypeDto(product);
        return new ProductDto(product.getId(),
                product.getName(),
                typeDto,
                product.getPrice(),
                product.getCount(),
                storeDto,
                product.getDescription());
    }
    private static StoreEntity getStore(ProductDto dto) {
        return new StoreEntity(
                dto.getStore().getId(),
                dto.getStore().getName(),
                dto.getStore().getDescription(),
                getUser(dto),
                dto.getStore().getCreatedAt()
        );
    }
    private static UserEntity getUser(ProductDto dto) {
        return new UserEntity(
                dto.getStore().getUser().getId(),
                dto.getStore().getUser().getCreateAt(),
                getRole(dto),
                dto.getStore().getUser().getFullName(),
                dto.getStore().getUser().getEmail(),
                dto.getStore().getUser().getPassword(),
                dto.getStore().getUser().getSentCode(),
                dto.getStore().getUser().getGender(),
                getAttachment(dto),
                dto.getStore().getUser().getCardNumber(),
                dto.getStore().getUser().getPhoneNumber()
        );
    }
    private static RoleEntity getRole(ProductDto dto) {
        return new RoleEntity(
                dto.getStore().getUser().getRole().getId(),
                dto.getStore().getUser().getRole().getName(),
                dto.getStore().getUser().getRole().getDescription()
        );
    }
    private static Attachment getAttachment(ProductDto dto) {
        return new Attachment(
                dto.getStore().getUser().getAttachment().getId(),
                dto.getStore().getUser().getAttachment().getOriginalName(),
                dto.getStore().getUser().getAttachment().getDescription(),
                dto.getStore().getUser().getAttachment().getSubmittedName(),
                dto.getStore().getUser().getAttachment().getFileUrl()
        );
    }
    private static Type getType(ProductDto dto) {
        return new Type(
                dto.getType().getId(),
                dto.getType().getName(),
                dto.getType().getDescription(),
                new Category()
        );
    }
    private static StoreDto getStoreDto(ProductEntity product) {
        return new StoreDto(
                product.getStore().getId(),
                product.getStore().getName(),
                product.getStore().getDescription(),
                getUserDto(product),
                product.getStore().getCreatedAt()
        );
    }
    private static UserDto getUserDto(ProductEntity product) {
        return new UserDto(
                product.getStore().getUser().getId(),
                product.getStore().getUser().getCreated_at(),
                getRoleDto(product),
                product.getStore().getUser().getFullName(),
                product.getStore().getUser().getEmail(),
                product.getStore().getUser().getPassword(),
                product.getStore().getUser().getSentCode(),
                product.getStore().getUser().getGender(),
                getAttachmentDto(product),
                product.getStore().getUser().getCardNumber(),
                product.getStore().getUser().getPhoneNumber()
        );
    }
    private static RoleDto getRoleDto(ProductEntity product) {
        return new RoleDto(
                product.getStore().getUser().getRole().getId(),
                product.getStore().getUser().getRole().getName(),
                product.getStore().getUser().getRole().getDescription()
        );
    }
    private static AttachmentDto getAttachmentDto(ProductEntity product) {
        return new AttachmentDto(
                product.getStore().getUser().getAttachment().getId(),
                product.getStore().getUser().getAttachment().getOriginalName(),
                product.getStore().getUser().getAttachment().getDescription(),
                product.getStore().getUser().getAttachment().getSubmittedName(),
                product.getStore().getUser().getAttachment().getFileUrl()
        );
    }
    private static TypeDto getTypeDto(ProductEntity product) {
        return new TypeDto(
                product.getType().getId(),
                product.getType().getName(),
                product.getType().getDescription()
        );
    }
}
