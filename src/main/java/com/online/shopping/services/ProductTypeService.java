package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.ProductSubcategory;
import com.online.shopping.entity.ProductType;
import com.online.shopping.enums.ProductStatus;
import com.online.shopping.exception.ProductSubcategoryNotFoundException;
import com.online.shopping.exception.ProductTypeNotFoundException;
import com.online.shopping.mapper.ProductTypeMapper;
import com.online.shopping.repository.ProductTypeRepository;
import com.online.shopping.repository.ProductSubcategoryRepository;
import com.online.shopping.requestdto.ProductTypeRequestDto;
import com.online.shopping.responsedto.ProductTypeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private ProductSubcategoryRepository subcategoryRepository;

    public List<ProductTypeResponseDto> getAllProductTypes() {
        return productTypeRepository.findAll().stream().map(productTypes -> productTypeMapper.convertEntityToDto(productTypes)).collect(Collectors.toList());
    }

    public ProductTypeResponseDto getSingleProductType(int productTypeId) {
        ProductType productType = productTypeRepository.findById(productTypeId).orElseThrow(() -> new ProductTypeNotFoundException(ErrorConstants.PRODUCT_TYPE_NOT_FOUND_ERROR + productTypeId));
        return productTypeMapper.convertEntityToDto(productType);
    }

    public List<ProductTypeResponseDto> getParticularSubcategoryProductTypes(int subcategoryId) {
        Optional<ProductSubcategory> subcategory = subcategoryRepository.findById(subcategoryId);
        if (subcategory.isPresent()) {
            return productTypeRepository.findAllByProductSubcategoryId(subcategoryId).stream().map(sb -> productTypeMapper.convertEntityToDto(sb)).collect(Collectors.toList());
        }
        throw new ProductSubcategoryNotFoundException(ErrorConstants.PRODUCT_SUBCATEGORY_NOT_EXIST_ERROR);
    }

    public ProductTypeResponseDto addProductType(ProductTypeRequestDto productTypeRequestDto) {
        Optional<ProductType> ProductTypeResponse = productTypeRepository.findById(productTypeRequestDto.getId());
        if (!ProductTypeResponse.isPresent()) {
            ProductSubcategory subcategory = subcategoryRepository.findById(productTypeRequestDto.getSubcategoryId()).orElseThrow(() -> new ProductSubcategoryNotFoundException(ErrorConstants.PRODUCT_SUBCATEGORY_NOT_EXIST_ERROR));
            ProductType productType = productTypeMapper.convertDtoToEntity(productTypeRequestDto);
            productType.setProductSubcategory(subcategory);
            productType.setStatus(ProductStatus.AVAILABLE);
            return productTypeMapper.convertEntityToDto(productTypeRepository.save(productType));
        } else {
            return productTypeMapper.convertEntityToDto(productTypeRepository.save(ProductTypeResponse.get()));
        }
    }

    public ProductTypeResponseDto updateProductTypeStatus(int productTypeId) {
        ProductType productType = productTypeRepository.findById(productTypeId).orElseThrow(() -> new ProductTypeNotFoundException(ErrorConstants.PRODUCT_TYPE_NOT_FOUND_ERROR + productTypeId));
        productType.setStatus(ProductStatus.INACTIVE);
        return productTypeMapper.convertEntityToDto(productTypeRepository.save(productType));
    }

    public String removeProductType(int productTypeId) {
        ProductType productType = productTypeRepository.findById(productTypeId).orElseThrow(() -> new ProductTypeNotFoundException(ErrorConstants.PRODUCT_TYPE_NOT_FOUND_ERROR + productTypeId));
        productTypeRepository.delete(productType);
        return "Successfully deleted the ProductType where id :" + productTypeId;
    }

}
