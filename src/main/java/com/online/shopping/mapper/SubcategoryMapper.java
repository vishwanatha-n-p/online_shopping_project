package com.online.shopping.mapper;

import com.online.shopping.entity.ProductSubcategory;
import com.online.shopping.requestdto.ProductSubcategoryRequestDto;
import com.online.shopping.responsedto.ProductSubcategoryResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductSubcategory convertDtoToEntity(ProductSubcategoryRequestDto productSubcategoryDto) {
        return new ProductSubcategory(productSubcategoryDto.getSubcategoryName());
    }

    public ProductSubcategoryResponseDto convertEntityToDto(ProductSubcategory productSubcategory) {
        return mapper.map(productSubcategory, ProductSubcategoryResponseDto.class);
    }

}
