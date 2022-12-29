package com.online.shopping.mapper;

import com.online.shopping.entity.ProductCategory;
import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.responsedto.ProductCategoryResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductCategory convertDtoToEntity(ProductCategoryRequestDto productCategoryDto) {
        return new ProductCategory(productCategoryDto.getCategoryName());
    }

    public ProductCategoryResponseDto convertEntityToDto(ProductCategory productCategory) {
        return mapper.map(productCategory, ProductCategoryResponseDto.class);
    }

}
