package com.online.shopping.mapper;

import com.online.shopping.entity.Product;
import com.online.shopping.requestdto.ProductRequestDto;
import com.online.shopping.responsedto.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    public Product convertDtoToEntity(ProductRequestDto productRequestDto) {
        return new Product(productRequestDto.getProductName(), productRequestDto.getColor(), productRequestDto.getProductCount());
    }

    public ProductResponseDto convertEntityToDto(Product product) {
        return mapper.map(product, ProductResponseDto.class);
    }

}
