package com.online.shopping.mapper;

import com.online.shopping.entity.ProductOrder;
import com.online.shopping.requestdto.OrderFromPriceDetailRequestDto;
import com.online.shopping.requestdto.OrderFromProductRequestDto;
import com.online.shopping.responsedto.ProductOrderResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductOrder convertDtoToEntity(OrderFromProductRequestDto orderDetailRequestDto) {
        return new ProductOrder(orderDetailRequestDto.getQuantity());
    }

    public ProductOrder convertDtoToEntity(OrderFromPriceDetailRequestDto orderDetailRequestDto) {
        return new ProductOrder(orderDetailRequestDto.getQuantity());
    }

    public ProductOrderResponseDto convertEntityToDto(ProductOrder orderDetails) {
        return mapper.map(orderDetails, ProductOrderResponseDto.class);
    }

}
