package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.ProductCategory;
import com.online.shopping.enums.ProductStatus;
import com.online.shopping.exception.GeneralException;
import com.online.shopping.exception.ProductCategoryNotFoundException;
import com.online.shopping.mapper.CategoryMapper;
import com.online.shopping.repository.ProductCategoryRepository;
import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.responsedto.ProductCategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public ProductCategoryResponseDto addCategory(ProductCategoryRequestDto categoryRequestDto) {
        Optional<ProductCategory> categoryRequest = categoryRepository.findByCategoryName(categoryRequestDto.getCategoryName());
        if (!categoryRequest.isPresent()) {
            ProductCategory categoryResponse = categoryMapper.convertDtoToEntity(categoryRequestDto);
            categoryResponse.setStatus(ProductStatus.ACTIVE);
            return categoryMapper.convertEntityToDto(categoryRepository.save(categoryResponse));
        }
        throw new GeneralException(ErrorConstants.PRODUCT_CATEGORY_EXIST_ERROR);
    }

    public List<ProductCategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(category -> categoryMapper.convertEntityToDto(category)).collect(Collectors.toList());
    }

    public ProductCategoryResponseDto getSingleCategory(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        return categoryMapper.convertEntityToDto(category);
    }

    public ProductCategoryResponseDto inactivateCategoryStatus(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        category.setStatus(ProductStatus.INACTIVE);
        return categoryMapper.convertEntityToDto(categoryRepository.save(category));
    }

    public ProductCategoryResponseDto activateCategoryStatus(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        category.setStatus(ProductStatus.ACTIVE);
        return categoryMapper.convertEntityToDto(categoryRepository.save(category));
    }

    public ProductCategoryResponseDto removeCategory(int categoryId) {
        ProductCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId));
        try {
            categoryRepository.delete(category);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(ErrorConstants.PRODUCT_CATEGORY_ALREADY_USED_ERROR);
        }
        return categoryMapper.convertEntityToDto(category);
    }

}
