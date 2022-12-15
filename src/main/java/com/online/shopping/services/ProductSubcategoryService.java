package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.enums.ProductStatus;
import com.online.shopping.exception.ProductCategoryNotFoundException;
import com.online.shopping.responsedto.ProductSubcategoryResponseDto;
import com.online.shopping.requestdto.SubcategoryRequestDto;
import com.online.shopping.entity.ProductCategory;
import com.online.shopping.entity.ProductSubcategory;
import com.online.shopping.exception.ProductSubcategoryNotFoundException;
import com.online.shopping.mapper.SubcategoryMapper;
import com.online.shopping.repository.ProductCategoryRepository;
import com.online.shopping.repository.ProductSubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductSubcategoryService {

    @Autowired
    private ProductSubcategoryRepository subcategoryRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private SubcategoryMapper subcategoryMapper;

    public List<ProductSubcategoryResponseDto> getAllSubcategory() {
        return subcategoryRepository.findAll().stream().map(subcategory -> subcategoryMapper.convertEntityToDto(subcategory)).collect(Collectors.toList());
    }

    public  List<ProductSubcategoryResponseDto> getParticularCategorySubcategories(int categoryId) {
        Optional<ProductCategory> category = categoryRepository.findById(categoryId);
        if(category.isPresent()) {
            return subcategoryRepository.findAllByProductCategoryId(categoryId).stream().map(sb -> subcategoryMapper.convertEntityToDto(sb)).collect(Collectors.toList());
        }
        throw new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_FOUND_ERROR + categoryId);
    }

    public ProductSubcategoryResponseDto getSingleSubcategory(int subcategoryId) {
            ProductSubcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(() -> new ProductSubcategoryNotFoundException(ErrorConstants.PRODUCT_SUBCATEGORY_NOT_FOUND_ERROR + subcategoryId));
        return subcategoryMapper.convertEntityToDto(subcategory);
    }

    public ProductSubcategoryResponseDto addSubcategory(SubcategoryRequestDto subcategoryRequestDto) {
        Optional<ProductSubcategory> subcategory = subcategoryRepository.findById(subcategoryRequestDto.getId());
        if (!subcategory.isPresent()) {
                ProductCategory category = categoryRepository.findById(subcategoryRequestDto.getCategoryId()).orElseThrow(() -> new ProductCategoryNotFoundException(ErrorConstants.PRODUCT_CATEGORY_NOT_EXIST_ERROR));
            ProductSubcategory subcategoryResponse = subcategoryMapper.convertDtoToEntity(subcategoryRequestDto);
            subcategoryResponse.setStatus(ProductStatus.ACTIVE);
            subcategoryResponse.setProductCategory(category);
            return subcategoryMapper.convertEntityToDto(subcategoryRepository.save(subcategoryResponse));
        } else {
            return subcategoryMapper.convertEntityToDto(subcategoryRepository.save(subcategory.get()));
        }
    }

    public ProductSubcategoryResponseDto updateSubcategoryStatus(int subcategoryId) {
        ProductSubcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(() -> new ProductSubcategoryNotFoundException(ErrorConstants.PRODUCT_SUBCATEGORY_NOT_FOUND_ERROR  + subcategoryId));
        subcategory.setStatus(ProductStatus.INACTIVE);
        return subcategoryMapper.convertEntityToDto(subcategoryRepository.save(subcategory));
    }

    public String removeSubcategory(int subcategoryId) {
        ProductSubcategory category = subcategoryRepository.findById(subcategoryId).orElseThrow(() -> new ProductSubcategoryNotFoundException(ErrorConstants.PRODUCT_SUBCATEGORY_NOT_FOUND_ERROR + subcategoryId));
        subcategoryRepository.delete(category);
        return "Successfully deleted the Subcategory, Where id:" + subcategoryId;
    }

}
