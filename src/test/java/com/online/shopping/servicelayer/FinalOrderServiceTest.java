package com.online.shopping.servicelayer;

import com.online.shopping.responsedto.FinalOrderResponseDto;
import com.online.shopping.responsedto.MyOrderResponseDto;
import com.online.shopping.services.FinalOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FinalOrderServiceTest {

    @Autowired
    private FinalOrderService finalOrderService;

    @Test
    public void test_getUserFinalOrder() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        List<FinalOrderResponseDto> finalOrders = finalOrderService.getUserFinalOrder(securityToken);
        assertThat(finalOrders.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_cancelFinalOrder() {
        int finalOrderId = 5;
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0ODc4NywiaWF0IjoxNjcyMjMwNzg3fQ.Ss5NBikVjWvUc6OPM-8pXrPHTSrVwZ_fGSy8wFzhlAsH42_3NFvhyfqg11V5ZCblO9ZKSXa0Yww-nSgtVBMwsQ";
        MyOrderResponseDto myOrderResponse = finalOrderService.cancelFinalOrder(finalOrderId, securityToken);
        assertThat(myOrderResponse).isNotNull();
    }

    @Test
    public void test_deliverFinalOrder() {
        int finalOrderId = 6;
        MyOrderResponseDto myOrderResponse = finalOrderService.deliverFinalOrder(finalOrderId);
        assertThat(myOrderResponse).isNotNull();
    }

}
