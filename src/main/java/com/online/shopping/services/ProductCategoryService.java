package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.ProductCategory;
import com.online.shopping.enums.ProductStatus;
import com.online.shopping.exception.ProductCategoryNotFoundException;
import com.online.shopping.mapper.CategoryMapper;
import com.online.shopping.mapper.SubcategoryMapper;
import com.online.shopping.repository.ProductCategoryRepository;
import com.online.shopping.repository.ProductSubcategoryRepository;
import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.responsedto.ProductCategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductSubcategoryRepository subcategoryRepository;

    @Autowired
    private SubcategoryMapper subcategoryMapper;

    public ProductCategoryResponseDto addCategory(ProductCategoryRequestDto categoryRequestDto) {
        Optional<ProductCategory> categoryRequest = categoryRepository.findById(categoryRequestDto.getId());
        if (!categoryRequest.isPresent()) {
            ProductCategory categoryResponse = categoryMapper.convertDtoToEntity(categoryRequestDto);
            categoryResponse.setStatus(ProductStatus.ACTIVE);
            return categoryMapper.convertEntityToDto(categoryRepository.save(categoryResponse));
        } else {
            return categoryMapper.convertEntityToDto(categoryRepository.save(categoryRequest.get()));
        }
    }

    public List<ProductCategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(category -> categoryMapper.convertEntityToDto(category)).collect(Collectors.toList());
    }

    public ProductCategoryResponseDto getSingleCategory(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        return categoryMapper.convertEntityToDto(category);
    }

    public ProductCategoryResponseDto updateCategoryStatus(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        category.setStatus(ProductStatus.INACTIVE);
        return categoryMapper.convertEntityToDto(categoryRepository.save(category));
    }

    public String removeCategory(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        categoryRepository.delete(category);
        return "Successfully deleted Product category, Where id:" + categoryId;
    }

}
