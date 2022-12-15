package com.online.shopping.mapper;

import com.online.shopping.entity.CartList;
import com.online.shopping.requestdto.CartListRequestDto;
import com.online.shopping.responsedto.CartListResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartListMapper {

    @Autowired
    private ModelMapper mapper;

    public CartList convertDtoToEntity(CartListRequestDto cartListRequestDto) {
        return new CartList();
    }

    public CartListResponseDto convertEntityToDto(CartList cartList) {
        return mapper.map(cartList, CartListResponseDto.class);
    }

}
