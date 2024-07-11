package uz.lee.onlineshoop.service;

import org.springframework.stereotype.Service;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.CategoryDto;
import uz.lee.onlineshoop.entity.Category;
import uz.lee.onlineshoop.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse(true, "categories", all);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return new ApiResponse(true, "category", category);
    }

    public ApiResponse updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse(false, "Category not found");
        }
        Category existingCategory = optionalCategory.get();

        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());

        categoryRepository.save(existingCategory);
        return new ApiResponse(true, "Category updated successfully");
    }

    public ApiResponse deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return new ApiResponse(true, "Category deleted successfully");
    }
}
