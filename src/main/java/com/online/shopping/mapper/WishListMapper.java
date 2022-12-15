package com.online.shopping.mapper;

import com.online.shopping.entity.WishList;
import com.online.shopping.requestdto.WishListRequestDto;
import com.online.shopping.responsedto.WishListResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishListMapper {

    @Autowired
    private ModelMapper mapper;

    public WishList convertDtoToEntity(WishListRequestDto wishListRequestDto) {
        return new WishList();
    }

    public WishListResponseDto convertEntityToDto(WishList wishList) {
        return mapper.map(wishList, WishListResponseDto.class);
    }

}
