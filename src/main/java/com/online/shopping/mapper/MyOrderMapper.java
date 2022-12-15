package com.online.shopping.mapper;

import com.online.shopping.entity.FinalOrder;
import com.online.shopping.entity.MyOrders;
import com.online.shopping.entity.Product;
import com.online.shopping.responsedto.MyOrderResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyOrderMapper {

    @Autowired
    private ModelMapper mapper;

    public MyOrders convertFinalOrderToMyOrder(FinalOrder finalOrder, Product product){
        return new MyOrders(product.getProductName(), product.getColor(), finalOrder.getQuantity(), finalOrder.getCost(), finalOrder.getOrderDate());
    }

    public MyOrderResponseDto convertEntityToDto(MyOrders myOrders){
        return mapper.map(myOrders, MyOrderResponseDto.class);
    }

}
