package com.online.shopping.unittestcases.services;

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
        int finalOrderId = 2;
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaXJhdCIsImV4cCI6MTY3MjMzNDcxMCwiaWF0IjoxNjcyMzE2NzEwfQ.V2ifYWI1G8XCV0S6rTnsiDwVGsEmYX0VM_Tpl-qkyYM9D_DSExdS-A3YWZKxwkpEwy51mLo4481WT6qdJBkjtA";
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
