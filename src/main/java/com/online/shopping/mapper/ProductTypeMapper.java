package com.online.shopping.mapper;

import com.online.shopping.entity.ProductType;
import com.online.shopping.requestdto.ProductTypeRequestDto;
import com.online.shopping.responsedto.ProductTypeResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductType convertDtoToEntity(ProductTypeRequestDto productTypeRequestDto) {
        return new ProductType(productTypeRequestDto.getProductType());
    }

    public ProductTypeResponseDto convertEntityToDto(ProductType productTypes) {
        return mapper.map(productTypes, ProductTypeResponseDto.class);
    }

}
