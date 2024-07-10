package uz.lee.onlineshoop.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.product.EditProductDto;
import uz.lee.onlineshoop.dto.product.ProductDto;
import uz.lee.onlineshoop.entity.ProductEntity;
import uz.lee.onlineshoop.entity.StoreEntity;
import uz.lee.onlineshoop.entity.Type;
import uz.lee.onlineshoop.repository.TypeRepository;
import uz.lee.onlineshoop.repository.ProductRepository;
import uz.lee.onlineshoop.repository.StoreEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreEntityRepository storeEntityRepository;
    private final TypeRepository typeRepository;

    private final int DEFAULT_PAGE = 1;
    private final int DEFAULT_SIZE = 10;

    public ProductService(ProductRepository productRepository,
                          StoreEntityRepository storeEntityRepository,
                          TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.storeEntityRepository = storeEntityRepository;
        this.typeRepository = typeRepository;
    }

    public ApiResponse getProducts(Integer page,Integer size) {
        if (size == null || size <= 0) {
            size = DEFAULT_SIZE;
        }
        if (page == null || page < 0) {
            page = DEFAULT_PAGE;
        }

        Pageable pageable  = PageRequest.of(page, size);
        Page<ProductEntity> all = productRepository.findAll(pageable);
        return new ApiResponse(true,"products", all);
    }

    public ApiResponse getProductById(Long id) {
        Optional<ProductEntity> byId = productRepository.findById(id);
        if (byId.isPresent()){
            ProductEntity product = byId.get();
            return new ApiResponse(true,"product: " + product.getId(),product);
        }
        return new ApiResponse(false,"Product is not exist in this id");
    }

    public ApiResponse saveProduct(ProductDto productDto) {
        ProductEntity product = new ProductEntity();
        Optional<StoreEntity> store = storeEntityRepository.findById(productDto.getStoreId());
        Optional<Type> type = typeRepository.findById(productDto.getTypeId());

        if (store.isEmpty()){
            return new ApiResponse(false,"Only store owners can add product!!!");
        }

        if (type.isEmpty()){
            return new ApiResponse(false,"There is no such kind of type");
        }

        product.setStore(store.get());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCount(productDto.getCount());
        product.setPrice(productDto.getPrice());
        product.setType(type.get());

        productRepository.save(product);

        return new ApiResponse(true,"Product saved successfully !!!");
    }

    public ApiResponse deleteProductById(Long id) {
        productRepository.deleteById(id);
        return new ApiResponse(true,"Product deleted successfully !!!");
    }

    public ApiResponse editProduct(Long id, EditProductDto productDto) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()){
            return new ApiResponse(false,"There is no product in this id[" + id + "]");
        }
        ProductEntity product = productOpt.get();

        Integer count = productDto.getCount();
        if (count != null){
           product.setCount(count);
        }
        String name = productDto.getName();
        if (name != null){
            product.setName(name);
        }
        String description = productDto.getDescription();
        if (description != null){
            product.setDescription(description);
        }
        Double price = productDto.getPrice();
        if (price != null){
            product.setPrice(price);
        }
        int typeId = productDto.getTypeId();
        Optional<Type> optionalType = typeRepository.findById(typeId);
        optionalType.ifPresent(product::setType);

        productRepository.save(product);

        return new ApiResponse(true,"Product saved successfully");
    }
}
