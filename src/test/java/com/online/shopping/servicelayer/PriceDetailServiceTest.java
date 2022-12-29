package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.PriceDetailRequestDto;
import com.online.shopping.responsedto.PriceDetailResponseDto;
import com.online.shopping.services.PriceDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PriceDetailServiceTest {

    @Autowired
    private PriceDetailService priceDetailService;

    @Test
    public void test_addPriceDetail() {
        PriceDetailRequestDto priceDetailRequest = new PriceDetailRequestDto(10, "Austermobile", 1, 68600L, 6, "30%%", 0, 150, 48020L);
        PriceDetailResponseDto priceDetailResponse = priceDetailService.addPriceDetail(priceDetailRequest);
        assertEquals(priceDetailRequest.getProductId(), priceDetailResponse.getProduct().getId());
        assertEquals(priceDetailRequest.getSellerName(), priceDetailResponse.getSellerName());
        assertEquals(priceDetailRequest.getCurrencyId(), priceDetailResponse.getCurrency().getId());
        assertEquals(priceDetailRequest.getPrice(), priceDetailResponse.getPrice());
        assertEquals(priceDetailRequest.getDiscount(), priceDetailResponse.getDiscount());
        assertEquals(priceDetailRequest.getDeliveryCharge(), priceDetailResponse.getDeliveryCharge());
        assertEquals(priceDetailRequest.getFinalPrice(), priceDetailResponse.getFinalPrice());
    }

    @Test
    public void test_getAllPriceDetail() {
        List<PriceDetailResponseDto> priceDetails = priceDetailService.getAllPriceDetail();
        assertThat(priceDetails.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSinglePriceDetail() {
        int priceDetailId = 1;
        PriceDetailResponseDto priceDetailResponse = priceDetailService.getSinglePriceDetail(priceDetailId);
        assertEquals(priceDetailId, priceDetailResponse.getId());
    }

    @Test
    public void test_getProductPriceDetail() {
        int productId = 6;
        List<PriceDetailResponseDto> priceDetails = priceDetailService.getProductPriceDetail(productId);
        assertThat(priceDetails.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_removePriceDetail() {
        int priceDetailId = 7;
        PriceDetailResponseDto priceDetail = priceDetailService.removePriceDetail(priceDetailId);
        assertEquals(priceDetailId, priceDetail.getId());
    }

}
