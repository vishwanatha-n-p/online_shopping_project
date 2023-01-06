package com.online.shopping.unittestcases.services;

import com.online.shopping.requestdto.OrderFromPriceDetailRequestDto;
import com.online.shopping.requestdto.OrderFromProductRequestDto;
import com.online.shopping.responsedto.ProductOrderResponseDto;
import com.online.shopping.services.ProductOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductOrderServiceTest {

    @Autowired
    private ProductOrderService productOrderService;

    @Test
    public void test_addProductOrder() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        OrderFromProductRequestDto orderFromProduct = new OrderFromProductRequestDto(9, 1);
        ProductOrderResponseDto productOrderResponse = productOrderService.addProductOrder(orderFromProduct, securityToken);
        assertEquals(orderFromProduct.getProductId(), productOrderResponse.getProduct().getId());
        assertEquals(orderFromProduct.getQuantity(), productOrderResponse.getQuantity());
    }

    @Test
    public void test_addProductFromPriceDetail() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjI0NDMzMSwiaWF0IjoxNjcyMjI2MzMxfQ.XmRnLoqfhXaeQCdWOgPR78FuvR0uJXmoCEBltYU4tkojcDPlG60v1qvfuE8RgdXqzzKVbLQ2aqN-u4aBVk_LTg";
        OrderFromPriceDetailRequestDto orderFromPriceDetail = new OrderFromPriceDetailRequestDto(3, 1);
        ProductOrderResponseDto productOrderResponse = productOrderService.addProductFromPriceDetail(orderFromPriceDetail, securityToken);
        assertThat(productOrderResponse).isNotNull();
    }

    @Test
    public void test_getAllProductOrders() {
        List<ProductOrderResponseDto> productOrders = productOrderService.getAllProductOrders();
        assertThat(productOrders.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getUserProductOrders() {
        String securityToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWthcyIsImV4cCI6MTY3MjIzMTg5OSwiaWF0IjoxNjcyMjEzODk5fQ.NgXGXP13mNLLkURlp6N0UZx740sinmjYyqaHD_TI1EmPgCf-QJUsuy8l2GVHykkVVBsJfdiXViOx3gzzDc6cEw";
        List<ProductOrderResponseDto> userProductOrders = productOrderService.getUserProductOrders(securityToken);
        assertThat(userProductOrders.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_removeProductOrder() {
        int productOrderId = 12;
        ProductOrderResponseDto productOrder = productOrderService.removeProductOrder(productOrderId);
        assertEquals(productOrderId, productOrder.getId());
    }

}

