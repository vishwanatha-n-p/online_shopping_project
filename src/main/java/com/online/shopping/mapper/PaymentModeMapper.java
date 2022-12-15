package com.online.shopping.mapper;

import com.online.shopping.entity.PaymentMode;
import com.online.shopping.requestdto.PaymentModeRequestDto;
import com.online.shopping.responsedto.PaymentModeResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentModeMapper {

    @Autowired
    private ModelMapper mapper;

    public PaymentMode convertDtoToEntity(PaymentModeRequestDto paymentModeRequestDto) {
        return mapper.map(paymentModeRequestDto, PaymentMode.class);
    }

    public PaymentModeResponseDto convertEntityToDto(PaymentMode paymentMode) {
        return mapper.map(paymentMode, PaymentModeResponseDto.class);
    }

}
