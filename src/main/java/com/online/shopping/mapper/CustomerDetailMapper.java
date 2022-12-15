package com.online.shopping.mapper;

import com.online.shopping.entity.CustomerDetail;
import com.online.shopping.requestdto.CustomerDetailRequestDto;
import com.online.shopping.responsedto.CustomerDetailResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailMapper {

    @Autowired
    private ModelMapper mapper;

    public CustomerDetail convertDtoToEntity(CustomerDetailRequestDto customerDetailDto) {
        return mapper.map(customerDetailDto, CustomerDetail.class);
    }

    public CustomerDetailResponseDto convertEntityToDto(CustomerDetail customerDetail) {
        return mapper.map(customerDetail, CustomerDetailResponseDto.class);
    }

}
