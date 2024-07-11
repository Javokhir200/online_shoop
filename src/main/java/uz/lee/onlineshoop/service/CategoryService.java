package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.entity.Category;
import uz.lee.onlineshoop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse(true,"categories",all);
    }
}
