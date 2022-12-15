package com.online.shopping.mapper;

import com.online.shopping.entity.Seller;
import com.online.shopping.requestdto.SellerRequestDto;
import com.online.shopping.responsedto.SellerResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public Seller convertDtoToEntity(SellerRequestDto sellerRequestDto) {
        return new Seller(sellerRequestDto.getSellerName(), sellerRequestDto.getService(), sellerRequestDto.getContactNumber(), sellerRequestDto.getEmail(), sellerRequestDto.getCity(), sellerRequestDto.getState(), sellerRequestDto.getPostalCode(), sellerRequestDto.getCountry());
    }

    public SellerResponseDto convertEntityToDto(Seller seller) {
        return mapper.map(seller, SellerResponseDto.class);
    }
}
