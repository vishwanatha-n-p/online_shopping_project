package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.PaymentModeRequestDto;
import com.online.shopping.responsedto.PaymentModeResponseDto;
import com.online.shopping.services.PaymentModeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentModeServiceTest {

    @Autowired
    private PaymentModeService paymentModeService;

    @Test
    public void test_addPaymentMode() {
        PaymentModeRequestDto paymentModeRequest = new PaymentModeRequestDto("Cash on Delivery");
        PaymentModeResponseDto paymentModeResponse = paymentModeService.addPaymentMode(paymentModeRequest);
        assertEquals(paymentModeRequest.getModeName(), paymentModeResponse.getModeName());
    }

    @Test
    public void test_getAllPaymentMode() {
        List<PaymentModeResponseDto> paymentModes = paymentModeService.getAllPaymentMode();
        assertThat(paymentModes.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSinglePaymentMode() {
        int paymentModeId = 2;
        PaymentModeResponseDto paymentModeResponse = paymentModeService.getSinglePaymentMode(paymentModeId);
        assertEquals(paymentModeId, paymentModeResponse.getId());
    }

    @Test
    public void test_removePaymentMode() {
        int paymentModeId = 4;
        PaymentModeResponseDto paymentMode = paymentModeService.removePaymentMode(paymentModeId);
        assertThat(paymentMode).isNotNull();
    }

}
