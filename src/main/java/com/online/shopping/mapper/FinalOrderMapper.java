package com.online.shopping.mapper;

import com.online.shopping.entity.FinalOrder;
import com.online.shopping.entity.ProductOrder;
import com.online.shopping.responsedto.FinalOrderResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinalOrderMapper {

    @Autowired
    private ModelMapper mapper;

    public FinalOrder convertProductOrderToFinalOrder(ProductOrder productOrder) {
        return mapper.map(productOrder, FinalOrder.class);
    }

    public FinalOrderResponseDto convertEntityToDto(FinalOrder finalOrder) {
        return mapper.map(finalOrder, FinalOrderResponseDto.class);
    }

}
