package com.online.shopping.mapper;

import com.online.shopping.responsedto.ProductCategoryResponseDto;
import com.online.shopping.requestdto.ProductCategoryRequestDto;
import com.online.shopping.entity.ProductCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductCategory convertDtoToEntity(ProductCategoryRequestDto productCategoryDto) {
        return mapper.map(productCategoryDto, ProductCategory.class);
    }

    public ProductCategoryResponseDto convertEntityToDto(ProductCategory productCategory) {
        return mapper.map(productCategory, ProductCategoryResponseDto.class);
    }

}
