package com.online.shopping.mapper;

import com.online.shopping.entity.PriceDetail;
import com.online.shopping.requestdto.PriceDetailRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceDetailMapper {

    @Autowired
    private ModelMapper mapper;

    public PriceDetail convertDtoToEntity(PriceDetailRequestDto priceDetailRequestDto) {
        return new PriceDetail(priceDetailRequestDto.getSellerName(), priceDetailRequestDto.getPrice(), priceDetailRequestDto.getAvailabilityCount(), priceDetailRequestDto.getDiscount(), priceDetailRequestDto.getSpecialOfferDiscount(), priceDetailRequestDto.getDeliveryCharge(), priceDetailRequestDto.getFinalPrice());
    }

    public PriceDetailResponseDto convertEntityToDto(PriceDetail priceDetail) {
        return mapper.map(priceDetail, PriceDetailResponseDto.class);
    }

}
