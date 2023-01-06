package com.online.shopping.unittestcases.services;

import com.online.shopping.responsedto.MyOrderResponseDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.services.MyOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MyOrderServiceTest {

    @Autowired
    private MyOrderService myOrderService;

    @Test
    public void test_getAllCustomerMyOrders() {
        List<MyOrderResponseDto> myOrders = myOrderService.getAllCustomerMyOrders();
        assertThat(myOrders.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getAllMyOrders() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjMwNzc5OCwiaWF0IjoxNjcyMjg5Nzk4fQ.EunDkC1j_ssxgJYMWlh2YPgAcyWkfjJFjP1sPq37LzktrLgk7CGPkqBykzaRYyq1RQReFxU8M8oAaS_H9onwvg";
        List<MyOrderResponseDto> myOrders = myOrderService.getAllMyOrders(securityToken);
        assertThat(myOrders.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleMyOrder() {
        int myOrderId = 2;
        MyOrderResponseDto myOrder = myOrderService.getSingleMyOrder(myOrderId);
        assertEquals(myOrderId, myOrder.getId());
    }

    @Test
    public void test_getProductPresentDetails() {
        int myOrderId = 3;
        List<PriceDetailResponseDto> priceDetails = myOrderService.getProductPresentDetails(myOrderId);
        assertThat(priceDetails.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_removeSingleMyOrder() {
        int myOrderId = 3;
        MyOrderResponseDto myOrderResponse = myOrderService.removeSingleMyOrder(myOrderId);
        assertEquals(myOrderId, myOrderResponse.getId());
    }

}
